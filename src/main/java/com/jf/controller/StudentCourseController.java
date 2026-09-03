package com.jf.controller;

import com.jf.common.ServerResponse;
import com.jf.pojo.Student;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.pojo.viewpojo.StudentCourseView;
import com.jf.service.ChooseService;
import com.jf.service.CourseService;
import com.jf.service.student.StudentService;
import com.jf.utils.Sm3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 学生选课控制器
 * 前后端分离模式，所有接口返回JSON数据
 */
@RestController
@RequestMapping("/api/student")
public class StudentCourseController {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChooseService chooseService;

    @Autowired
    private StudentService studentService;

    /**
     * 获取课程列表
     * 支持token参数或者session认证
     */
    @GetMapping("/course")
    public ServerResponse<List<CourseView>> getCourse(
            @RequestParam(required = false) String term, 
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                // 通过token获取学生信息
                student = studentService.findStudentByToken(token);
                if (student != null) {
                    logger.info("通过token获取到学生信息: {}", student.getStudentName());
                }
            } catch (Exception e) {
                logger.error("通过token获取学生信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }
        
        // 如果存在有效的学生信息
        if (student != null) {
            List<CourseView> courseViewList = courseService.getCourseView(term, student.getStudentId());
            return ServerResponse.createBySuccess(courseViewList);
        } else {
            return ServerResponse.createByErrorMessage("请先登录");
        }
    }

    /**
     * 学生选课
     * 支持token参数或者session认证
     */
    @PostMapping("/course")
    public ServerResponse<Object> chooseCourse(
            @RequestParam Integer courseId, 
            @RequestParam String term, 
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                student = studentService.findStudentByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取学生信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }
        
        // 如果存在有效的学生信息
        if (student != null) {
            return chooseService.chooseCourse(student.getStudentId(), courseId, new Date(), new Date(), term);
        } else {
            return ServerResponse.createByErrorMessage("请先登录");
        }
    }

    /**
     * 学生退课
     * 支持token参数或者session认证
     */
    @DeleteMapping("/course/{courseId}")
    public ServerResponse<Object> deleteCourse(
            @PathVariable Integer courseId, 
            @RequestParam(required = false) String token,
            @RequestParam(required = false) String term,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                student = studentService.findStudentByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取学生信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }
        
        // 如果存在有效的学生信息
        if (student != null) {
            return chooseService.deleteCourse(student.getStudentId(), courseId, new Date(), new Date());
        } else {
            return ServerResponse.createByErrorMessage("请先登录");
        }
    }

    /**
     * 获取已选课程列表
     * 支持token参数或者session认证
     */
    @GetMapping("/my-courses")
    public ServerResponse<List<StudentCourseView>> getMyCourse(
            @RequestParam String term, 
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                student = studentService.findStudentByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取学生信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }
        
        // 如果存在有效的学生信息
        if (student != null) {
            List<StudentCourseView> studentCourseViewList = courseService.getMyCourse(student.getStudentId(), term);
            return ServerResponse.createBySuccess(studentCourseViewList);
        } else {
            return ServerResponse.createByErrorMessage("请先登录");
        }
    }
    
    /**
     * 获取学生信息
     * 支持token参数或者session认证
     */
    @GetMapping("/info")
    public ServerResponse<Student> getStudentInfo(
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                student = studentService.findStudentByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取学生信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }
        
        // 如果存在有效的学生信息
        if (student != null) {
            // 安全起见，清除密码信息
            student.setPassword(null);
            return ServerResponse.createBySuccess(student);
        } else {
            return ServerResponse.createByErrorMessage("请先登录");
        }
    }

    /**
     * 按课程类型查询
     */
    @GetMapping("/course/type/{type}")
    public ServerResponse<List<CourseView>> getCourseByType(@PathVariable String type, @RequestParam String term, HttpSession session) {
        Object userLogin = session.getAttribute("UserLogin");
        if (userLogin != null) {
            if (userLogin instanceof Student) {
                List<CourseView> courseViewList = courseService.getCourseViewByType(type, ((Student) userLogin).getStudentId(), term);
                return ServerResponse.createBySuccess(courseViewList);
            } else {
                return ServerResponse.createByErrorMessage("您是老师或管理员不能参与选课");
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了,请重新登录");
        }
    }

    /**
     * 按专业查询课程
     */
    @GetMapping("/course/major/{majorId}")
    public ServerResponse<List<CourseView>> getCourseByMajor(@PathVariable Integer majorId, @RequestParam String term, HttpSession session) {
        Object userLogin = session.getAttribute("UserLogin");
        if (userLogin != null) {
            if (userLogin instanceof Student) {
                List<CourseView> courseViewList = courseService.getCourseByMajor(majorId, ((Student) userLogin).getStudentId(), term);
                return ServerResponse.createBySuccess(courseViewList);
            } else {
                return ServerResponse.createByErrorMessage("您是老师或管理员不能参与选课");
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了,请重新登录");
        }
    }

    /**
     * 按课程名称模糊查询
     */
    @GetMapping("/course/search")
    public ServerResponse<List<CourseView>> getCourseByCourseName(@RequestParam String courseName, @RequestParam String term, HttpSession session) {
        Object userLogin = session.getAttribute("UserLogin");
        if (userLogin != null) {
            if (userLogin instanceof Student) {
                if (courseName == null || courseName.trim().isEmpty()) {
                    return getCourse(term, null, session);
                }
                List<CourseView> courseViewList = courseService.getCourseByCourseName(courseName, ((Student) userLogin).getStudentId(), term);
                return ServerResponse.createBySuccess(courseViewList);
            } else {
                return ServerResponse.createByErrorMessage("您是老师或管理员不能参与选课");
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了,请重新登录");
        }
    }

    /**
     * 按条件组合查询课程
     */
    @GetMapping("/course/condition")
    public ServerResponse<List<CourseView>> getCourseByCondition(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer major,
            @RequestParam String term,
            HttpSession session) {
        
        Object userLogin = session.getAttribute("UserLogin");
        if (userLogin != null) {
            if (userLogin instanceof Student) {
                if (type == null || type.isEmpty() || "全部".equals(type)) {
                    if (major == null || major == 0) {
                        return getCourse(term, null, session);
                    } else if (major > 0) {
                        return getCourseByMajor(major, term, session);
                    } else {
                        return ServerResponse.createByErrorMessage("专业id有误");
                    }
                } else {
                    if (major == null || major == 0) {
                        return getCourseByType(type, term, session);
                    } else if (major > 0) {
                        List<CourseView> courseViewList = courseService.getCourseByTypeAndMajor(
                                type, major, ((Student) userLogin).getStudentId(), term);
                        return ServerResponse.createBySuccess(courseViewList);
                    } else {
                        return ServerResponse.createByErrorMessage("专业id有误");
                    }
                }
            } else {
                return ServerResponse.createByErrorMessage("您是老师或管理员不能参与选课");
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了,请重新登录");
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/password")
    public ServerResponse<String> updatePassword(
            @RequestParam String oldpass,
            @RequestParam String newpass,
            @RequestParam String newpass2,
            @RequestParam(required = false) String token,
            HttpSession session) {
        
        // 先尝试 token 获取当前学生
        Student student = null;
        if (token != null && !token.isEmpty()) {
            try {
                student = studentService.findStudentByToken(token);
            } catch (Exception ignored) {}
        }
        // 再降级用 session
        if (student == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin instanceof Student) {
                student = (Student) userLogin;
            }
        }

        if (student == null) {
            return ServerResponse.createByErrorMessage("登录过期了,请重新登录");
        }

        try {
            // 验证旧密码
            if (!Sm3.checkpassword(oldpass, student.getStudentPassword())) {
                return ServerResponse.createByErrorMessage("原密码错误");
            }
            // 验证新密码一致性
            if (!newpass.equals(newpass2)) {
                return ServerResponse.createByErrorMessage("两次密码不一致");
            }
            // 更新密码
            String encodedPassword = Sm3.EncoderBySm3(newpass);
            int result = studentService.updatePassword(student.getStudentNo(), encodedPassword);
            if (result > 0) {
                student.setStudentPassword(encodedPassword);
                session.setAttribute("UserLogin", student);
                return ServerResponse.createBySuccessMessage("密码修改成功");
            } else {
                return ServerResponse.createByErrorMessage("密码修改失败");
            }
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("密码修改过程中发生错误: " + e.getMessage());
        }
    }
} 