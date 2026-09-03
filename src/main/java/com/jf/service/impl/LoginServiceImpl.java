package com.jf.service.impl;

import com.jf.mapper.AdminMapper;
import com.jf.mapper.StudentMapper;
import com.jf.mapper.TeacherMapper;
import com.jf.pojo.Admin;
import com.jf.pojo.Student;
import com.jf.pojo.Teacher;
import com.jf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Student selectStudentByStudentNo(String studentNo) {
        return studentMapper.selectByStudentNo(studentNo);
    }

    @Override
    public Admin selectAdminByAdminNo(String adminNo) {
        return adminMapper.selectByAdminNo(adminNo);
    }

    @Override
    public Teacher selectTeacherByTeacherNo(String teacherNo) {
        return teacherMapper.selectByTeacherNo(teacherNo);
    }
} 