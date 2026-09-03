package com.jf.service.student.impl;

import com.jf.mapper.StudentMapper;
import com.jf.pojo.Student;
import com.jf.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int updatePassword(String studentNo, String newpass) {
        return studentMapper.updatePasswordByStudentNo(studentNo, newpass, new Date());
    }

    @Override
    public String getPassword(Integer studentId) {
        return studentMapper.selectPasswordByStudentId(studentId);
    }

    @Override
    public Student getUserIsExist(String userno, String email) {
        return studentMapper.selectByUserNoAndEmail(userno, email);
    }

    @Override
    public Student findStudentByToken(String token) {
        try {
            // 这里简单实现，可以根据实际情况调整
            // 可能的实现方式：
            // 1. 从Redis中根据token获取学生信息
            // 2. 从JWT中解析学生ID，然后查询数据库
            // 3. 从其他缓存或会话存储中获取
            
            // 这里假设token格式为"student_学号"，实际项目中应使用更安全的方式
            if (token != null && token.startsWith("student_")) {
                String studentNo = token.substring("student_".length());
                return studentMapper.selectByStudentNo(studentNo);
            }
            
            // 尝试直接用token作为学号查询
            Student student = studentMapper.selectByStudentNo(token);
            if (student != null) {
                return student;
            }
            
            // 如果没有找到对应的学生信息，返回null
            return null;
        } catch (Exception e) {
            logger.error("根据token查找学生失败: {}", e.getMessage());
            return null;
        }
    }
} 