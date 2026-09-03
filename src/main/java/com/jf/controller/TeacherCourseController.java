package com.jf.controller;

import com.jf.common.ServerResponse;
import com.jf.pojo.Teacher;
import com.jf.pojo.viewpojo.ChooseGrade;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.service.ChooseService;
import com.jf.service.teacher.TeacherCourseService;
import com.jf.service.teacher.TeacherService;
import com.jf.utils.Sm3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 教师课程控制器
 * 前后端分离模式，所有接口返回JSON数据
 */
@RestController
@RequestMapping("/api/teacher")
public class TeacherCourseController {
    
    private static final Logger logger = LoggerFactory.getLogger(TeacherCourseController.class);

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private ChooseService chooseService;

    /**
     * 获取教师课程列表
     * 支持token参数或者session认证
     */
    @GetMapping("/courses")
    public ServerResponse<List<CourseView>> getCourses(
            @RequestParam(required = false) String token,
            HttpSession session) {
        
        // 尝试通过token获取用户
        Teacher teacher = null;
        if (token != null && !token.isEmpty()) {
            try {
                // 通过token获取教师信息
                teacher = teacherService.findTeacherByToken(token);
                if (teacher != null) {
                    logger.info("通过token获取到教师信息: {}", teacher.getTeacherName());
                }
            } catch (Exception e) {
                logger.error("通过token获取教师信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (teacher == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Teacher) {
                teacher = (Teacher) userLogin;
            }
        }
        
        // 如果存在有效的教师信息
        if (teacher != null) {
            List<CourseView> courses = teacherCourseService.getCourse(teacher.getTeacherId());
            for (CourseView courseView : courses) {
                courseView.setTerm(getTermString(courseView));
            }
            return ServerResponse.createBySuccess(courses);
        } else {
            return ServerResponse.createByErrorMessage("登录过期了，请重新登录");
        }
    }

    /**
     * 根据课程ID获取选课学生列表
     * 支持token参数或者session认证
     */
    @GetMapping("/course/{courseId}/students")
    public ServerResponse<List<ChooseGrade>> getCourseStudents(
            @PathVariable Integer courseId, 
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Teacher teacher = null;
        if (token != null && !token.isEmpty()) {
            try {
                teacher = teacherService.findTeacherByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取教师信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (teacher == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Teacher) {
                teacher = (Teacher) userLogin;
            }
        }
        
        // 如果存在有效的教师信息
        if (teacher != null) {
            try {
                List<ChooseGrade> chooseGrades = teacherCourseService.selectByCourseId(courseId);
                return ServerResponse.createBySuccess(chooseGrades);
            } catch (Exception e) {
                return ServerResponse.createByErrorMessage("查询选课学生信息失败: " + e.getMessage());
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了，请重新登录");
        }
    }

    /**
     * 获取选课记录详情
     * 支持token参数或者session认证
     */
    @GetMapping("/grade/{chooseId}")
    public ServerResponse<ChooseGrade> getChooseDetail(
            @PathVariable Integer chooseId, 
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Teacher teacher = null;
        if (token != null && !token.isEmpty()) {
            try {
                teacher = teacherService.findTeacherByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取教师信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (teacher == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Teacher) {
                teacher = (Teacher) userLogin;
            }
        }
        
        // 如果存在有效的教师信息
        if (teacher != null) {
            ChooseGrade chooseGrade = teacherCourseService.getChooseByChooseId(chooseId);
            if (chooseGrade != null) {
                return ServerResponse.createBySuccess(chooseGrade);
            } else {
                return ServerResponse.createByErrorMessage("找不到该选课记录");
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了，请重新登录");
        }
    }

    /**
     * 更新学生成绩
     * 支持token参数或者session认证
     */
    @PutMapping("/grade/{chooseId}")
    public ServerResponse<String> updateGrade(
            @PathVariable Integer chooseId, 
            @RequestParam Float usualGrade, 
            @RequestParam Float examGrade,
            @RequestParam(required = false) String token,
            HttpSession session) {
            
        // 尝试通过token获取用户
        Teacher teacher = null;
        if (token != null && !token.isEmpty()) {
            try {
                teacher = teacherService.findTeacherByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取教师信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (teacher == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Teacher) {
                teacher = (Teacher) userLogin;
            }
        }
        
        // 如果存在有效的教师信息
        if (teacher != null) {
            // 验证输入
            if (usualGrade < 0 || usualGrade > 100 || examGrade < 0 || examGrade > 100) {
                return ServerResponse.createByErrorMessage("成绩范围应为0-100");
            }
            
            // 计算总成绩：平时成绩占40%，考试成绩占60%
            float allGrade = usualGrade * 0.4f + examGrade * 0.6f;
            
            return chooseService.updateGrade(chooseId, usualGrade, examGrade, allGrade, new Date());
        } else {
            return ServerResponse.createByErrorMessage("登录过期了，请重新登录");
        }
    }

    /**
     * 修改密码
     * 支持token参数或者session认证
     */
    @PutMapping("/password")
    public ServerResponse<String> updatePassword(
            @RequestParam String oldpass, 
            @RequestParam String newpass, 
            @RequestParam String newpass2,
            @RequestParam(required = false) String token,
            HttpSession session) {
        
        // 验证新密码长度
        if (newpass.length() < 6 || newpass.length() > 16) {
            return ServerResponse.createByErrorMessage("新密码长度为6-16位");
        }
        
        // 验证输入是否为空
        if (oldpass == null || oldpass.trim().isEmpty() || 
            newpass == null || newpass.trim().isEmpty() || 
            newpass2 == null || newpass2.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("输入的内容有空值");
        }
        
        // 尝试通过token获取用户
        Teacher teacher = null;
        if (token != null && !token.isEmpty()) {
            try {
                teacher = teacherService.findTeacherByToken(token);
            } catch (Exception e) {
                logger.error("通过token获取教师信息失败: {}", e.getMessage());
            }
        }
        
        // 如果token方式失败，尝试从session获取
        if (teacher == null) {
            Object userLogin = session.getAttribute("UserLogin");
            if (userLogin != null && userLogin instanceof Teacher) {
                teacher = (Teacher) userLogin;
            }
        }
        
        // 如果存在有效的教师信息
        if (teacher != null) {
            // 验证两次密码是否一致
            if (!newpass.equals(newpass2)) {
                return ServerResponse.createByErrorMessage("两次密码不一致");
            }
            
            try {
                String password = teacherService.getPassword(teacher.getTeacherId());
                
                // 验证旧密码
                if (Sm3.checkpassword(oldpass.trim(), password)) {
                    // 更新密码
                    String encodedPassword = Sm3.EncoderBySm3(newpass.trim());
                    int result = teacherService.updatePassword(
                            teacher.getTeacherNo(), 
                            encodedPassword
                    );
                    
                    if (result > 0) {
                        // 更新Session中的用户对象
                        teacher.setTeacherPassword(encodedPassword);
                        session.setAttribute("UserLogin", teacher);
                        return ServerResponse.createBySuccessMessage("密码修改成功");
                    } else {
                        return ServerResponse.createByErrorMessage("密码修改失败");
                    }
                } else {
                    return ServerResponse.createByErrorMessage("原密码错误");
                }
            } catch (Exception e) {
                return ServerResponse.createByErrorMessage("密码修改过程中发生错误: " + e.getMessage());
            }
        } else {
            return ServerResponse.createByErrorMessage("登录过期了，请重新登录");
        }
    }

    /**
     * 获取学期的中文表示
     */
    private String getTermString(CourseView courseView) {
        switch (courseView.getTerm()) {
            case "1-1":
                return "大一上学期";
            case "1-2":
                return "大一下学期";
            case "2-1":
                return "大二上学期";
            case "2-2":
                return "大二下学期";
            case "3-1":
                return "大三上学期";
            case "3-2":
                return "大三下学期";
            case "4-1":
                return "大四上学期";
            case "4-2":
                return "大四下学期";
            case "5-1":
                return "大五上学期";
            case "5-2":
                return "大五下学期";
            default:
                return "无";
        }
    }
} 