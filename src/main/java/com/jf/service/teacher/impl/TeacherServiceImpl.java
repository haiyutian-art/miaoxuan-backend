package com.jf.service.teacher.impl;

import com.jf.mapper.TeacherMapper;
import com.jf.pojo.Teacher;
import com.jf.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 教师服务实现类
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getUserIsExist(String userno, String email) {
        return teacherMapper.selectByUserNoAndEmail(userno, email);
    }

    @Override
    public String getPassword(Integer teacherId) {
        return teacherMapper.selectPasswordByTeacherId(teacherId);
    }

    @Override
    public int updatePassword(String teacherNo, String newpass) {
        return teacherMapper.updatePasswordByTeacherNo(teacherNo, newpass, new Date());
    }
    
    @Override
    public Teacher findTeacherByToken(String token) {
        try {
            // 这里简单实现，可以根据实际情况调整
            // 可能的实现方式：
            // 1. 从Redis中根据token获取教师信息
            // 2. 从JWT中解析教师ID，然后查询数据库
            // 3. 从其他缓存或会话存储中获取
            
            // 这里假设token格式为"teacher_工号"，实际项目中应使用更安全的方式
            if (token != null && token.startsWith("teacher_")) {
                String teacherNo = token.substring("teacher_".length());
                return teacherMapper.selectByTeacherNo(teacherNo);
            }
            
            // 尝试直接用token作为工号查询
            Teacher teacher = teacherMapper.selectByTeacherNo(token);
            if (teacher != null) {
                return teacher;
            }
            
            // 如果没有找到对应的教师信息，返回null
            return null;
        } catch (Exception e) {
            logger.error("根据token查找教师失败: {}", e.getMessage());
            return null;
        }
    }
} 