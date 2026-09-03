package com.jf.controller;

import com.jf.common.ServerResponse;
import com.jf.pojo.Admin;
import com.jf.pojo.Student;
import com.jf.pojo.Teacher;
import com.jf.pojo.viewpojo.Own;
import com.jf.service.EmailService;
import com.jf.service.LoginService;
import com.jf.service.student.StudentService;
import com.jf.service.teacher.TeacherService;
import com.jf.utils.Sm3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录认证控制器
 * 前后端分离模式，所有接口返回JSON数据
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ServerResponse<Map<String, String>> doLogin(@RequestParam String username, 
                                         @RequestParam String password, 
                                         @RequestParam String role, 
                                         HttpSession session) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("请输入合法用户名或密码");
        }
        
        if (role == null || role.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("请选择角色");
        } else {
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("message", "登录成功");
            
            switch (role) {
                case "student":
                    Student student = loginService.selectStudentByStudentNo(username);
                    if (student != null) {
                        try {
                            if (Sm3.checkpassword(password, student.getStudentPassword())) {
                                student.setStudentPassword("");//密码置空
                                session.setAttribute("UserLogin", student);
                                
                                // 创建并返回token（简单实现，实际中应使用更安全的方式）
                                // 这里直接使用学号作为token，简单实现
                                resultMap.put("token", student.getStudentNo());
                                return ServerResponse.createBySuccess(resultMap);
                            } else {
                                return ServerResponse.createByErrorMessage("用户名或密码错误");
                            }
                        } catch (UnsupportedEncodingException e) {
                            return ServerResponse.createByErrorMessage("登录失败");
                        }
                    } else {
                        return ServerResponse.createByErrorMessage("用户名或密码错误");
                    }
                    
                case "teacher":
                    Teacher teacher = loginService.selectTeacherByTeacherNo(username);
                    if (teacher != null) {
                        try {
                            if (Sm3.checkpassword(password, teacher.getTeacherPassword())) {
                                teacher.setTeacherPassword("");//密码置空
                                session.setAttribute("UserLogin", teacher);
                                
                                // 创建并返回token（简单实现，实际中应使用更安全的方式）
                                // 这里直接使用工号作为token，简单实现
                                resultMap.put("token", teacher.getTeacherNo());
                                return ServerResponse.createBySuccess(resultMap);
                            } else {
                                return ServerResponse.createByErrorMessage("用户名或密码错误");
                            }
                        } catch (UnsupportedEncodingException e) {
                            return ServerResponse.createByErrorMessage("登录失败");
                        }
                    } else {
                        return ServerResponse.createByErrorMessage("用户名或密码错误");
                    }

                case "admin":
                    Admin admin = loginService.selectAdminByAdminNo(username);
                    if (admin != null) {
                        try {
                            if (Sm3.checkpassword(password, admin.getAdminPassword())) {
                                admin.setAdminPassword("");//密码置空
                                session.setAttribute("UserLogin", admin);
                                
                                // 创建并返回token（简单实现，实际中应使用更安全的方式）
                                // 这里直接使用管理员号作为token，简单实现
                                resultMap.put("token", admin.getAdminNo());
                                return ServerResponse.createBySuccess(resultMap);
                            } else {
                                return ServerResponse.createByErrorMessage("用户名或密码错误");
                            }
                        } catch (UnsupportedEncodingException e) {
                            return ServerResponse.createByErrorMessage("登录失败");
                        }
                    } else {
                        return ServerResponse.createByErrorMessage("用户名或密码错误");
                    }

                default:
                    return ServerResponse.createByErrorMessage("请选择有效角色");
            }
        }
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public ServerResponse<String> logout(HttpSession session) {
        session.invalidate();
        return ServerResponse.createBySuccess("登出成功");
    }

    /**
     * 获取当前用户信息
     * 支持token参数或者session认证
     */
    @GetMapping("/user/info")
    public ServerResponse<Own> getUserInfo(
            @RequestParam(required = false) String token,
            HttpSession session) {
        
        // 尝试通过token获取用户
        if (token != null && !token.isEmpty()) {
            // 尝试获取学生信息
            Student student = studentService.findStudentByToken(token);
            if (student != null) {
                Own own = new Own();
                own.setUserNo(student.getStudentNo());
                own.setUsername(student.getStudentName());
                own.setSex(student.getStudentSex());
                own.setPhone(student.getStudentPhone());
                own.setEmail(student.getStudentEmail());
                own.setRole("student");
                own.setId(student.getStudentId());
                return ServerResponse.createBySuccess(own);
            }
            
            // 尝试获取教师信息
            Teacher teacher = teacherService.findTeacherByToken(token);
            if (teacher != null) {
                Own own = new Own();
                own.setUserNo(teacher.getTeacherNo());
                own.setUsername(teacher.getTeacherName());
                own.setSex(teacher.getTeacherSex());
                own.setPhone(teacher.getTeacherPhone());
                own.setEmail(teacher.getTeacherEmail());
                own.setRole("teacher");
                own.setId(teacher.getTeacherId());
                return ServerResponse.createBySuccess(own);
            }
            
            // 管理员信息获取可以在这里添加
        }
        
        // 如果token不存在或者无效，尝试从session获取
        Object userLogin = session.getAttribute("UserLogin");
        if (userLogin != null) {
            Own own = new Own();
            if (userLogin instanceof Student) {
                Student student = (Student) userLogin;
                own.setUserNo(student.getStudentNo());
                own.setUsername(student.getStudentName());
                own.setSex(student.getStudentSex());
                own.setPhone(student.getStudentPhone());
                own.setEmail(student.getStudentEmail());
                own.setRole("student");
                own.setId(student.getStudentId());
                return ServerResponse.createBySuccess(own);
            } else if (userLogin instanceof Teacher) {
                Teacher teacher = (Teacher) userLogin;
                own.setUserNo(teacher.getTeacherNo());
                own.setUsername(teacher.getTeacherName());
                own.setSex(teacher.getTeacherSex());
                own.setPhone(teacher.getTeacherPhone());
                own.setEmail(teacher.getTeacherEmail());
                own.setRole("teacher");
                own.setId(teacher.getTeacherId());
                return ServerResponse.createBySuccess(own);
            } else if (userLogin instanceof Admin) {
                Admin admin = (Admin) userLogin;
                own.setUserNo(admin.getAdminNo());
                own.setUsername(admin.getAdminName());
                own.setPhone(admin.getAdminPhone());
                own.setEmail(admin.getAdminEmail());
                own.setRole("admin");
                own.setId(admin.getAdminId());
                return ServerResponse.createBySuccess(own);
            }
        }
        return ServerResponse.createByErrorMessage("登录信息已过期，请重新登录");
    }

    /**
     * 重置密码
     */
    @PostMapping("/reset-password")
    public ServerResponse<String> resetPassword(
            @RequestParam String userno, 
            @RequestParam String email, 
            @RequestParam String yzm, 
            @RequestParam String role, 
            @RequestParam String newpass, 
            @RequestParam String newpass2) {
        
        // 验证参数
        if (userno == null || userno.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            yzm == null || yzm.trim().isEmpty() || 
            role == null || role.trim().isEmpty() || 
            newpass == null || newpass.trim().isEmpty() || 
            newpass2 == null || newpass2.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("所有字段均为必填");
        }
        
        // 验证密码一致性
        if (!newpass.equals(newpass2)) {
            return ServerResponse.createByErrorMessage("两次密码不一致");
        }

        // 验证密码长度
        if (newpass.length() < 6 || newpass.length() > 16) {
            return ServerResponse.createByErrorMessage("密码长度必须为6-16位");
        }

        // 验证验证码
        String key = userno + "-" + role;
        String redisYZM = stringRedisTemplate.opsForValue().get(key);
        if (redisYZM == null || !redisYZM.equals(yzm)) {
            return ServerResponse.createByErrorMessage("验证码无效或已过期");
        }

        // 验证用户存在性
        if (!getUserIsExist(role, userno, email)) {
            return ServerResponse.createByErrorMessage("找不到匹配的用户");
        }

        // 根据角色重置密码
        try {
            String encodedPassword = Sm3.EncoderBySm3(newpass);
            int result = 0;
            switch (role) {
                case "student":
                    result = studentService.updatePassword(userno, encodedPassword);
                    break;
                case "teacher":
                    result = teacherService.updatePassword(userno, encodedPassword);
                    break;
                case "admin":
                    // 管理员密码重置逻辑
                    break;
            }
            
            if (result > 0) {
                // 删除Redis中的验证码
                stringRedisTemplate.delete(key);
                return ServerResponse.createBySuccessMessage("密码重置成功");
            } else {
                return ServerResponse.createByErrorMessage("密码重置失败");
            }
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("密码重置过程中发生错误");
        }
    }

    /**
     * 获取验证码
     */
    @PostMapping("/verification-code")
    public ServerResponse<String> getVerificationCode(
            @RequestParam String userno, 
            @RequestParam String email, 
            @RequestParam String role) {
        
        // 验证参数
        if (userno == null || userno.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            role == null || role.trim().isEmpty()) {
            return ServerResponse.createByErrorMessage("用户编号、邮箱和角色均为必填");
        }

        // 验证用户存在性
        if (!getUserIsExist(role, userno, email)) {
            return ServerResponse.createByErrorMessage("找不到匹配的用户");
        }

        // 生成验证码
        String verificationCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        String key = userno + "-" + role;
        
        // 将验证码存入Redis，有效期5分钟
        stringRedisTemplate.opsForValue().set(key, verificationCode, 5, TimeUnit.MINUTES);
        
        // 发送验证码到邮箱
        boolean sendResult = emailService.sendEmail(email, "密码重置验证码", "您的验证码是：" + verificationCode + "，有效期5分钟。");
        
        if (sendResult) {
            return ServerResponse.createBySuccessMessage("验证码已发送到您的邮箱");
        } else {
            return ServerResponse.createByErrorMessage("验证码发送失败");
        }
    }

    /**
     * 验证用户是否存在
     */
    private boolean getUserIsExist(String role, String userno, String email) {
        switch (role) {
            case "student":
                Student student = loginService.selectStudentByStudentNo(userno);
                return student != null && email.equals(student.getStudentEmail());
            case "teacher":
                Teacher teacher = loginService.selectTeacherByTeacherNo(userno);
                return teacher != null && email.equals(teacher.getTeacherEmail());
            case "admin":
                Admin admin = loginService.selectAdminByAdminNo(userno);
                return admin != null && email.equals(admin.getAdminEmail());
            default:
                return false;
        }
    }
} 