package com.jf.service.student;

import com.jf.pojo.Student;

/**
 * 学生服务接口
 * 处理学生相关功能
 */
public interface StudentService {

    /**
     * 更新学生密码
     * @param studentNo 学号
     * @param newpass 新密码
     * @return 影响行数
     */
    int updatePassword(String studentNo, String newpass);

    /**
     * 获取学生密码
     * @param studentId 学生ID
     * @return 密码
     */
    String getPassword(Integer studentId);

    /**
     * 根据学号和邮箱查询学生
     * @param userno 学号
     * @param email 邮箱
     * @return 学生信息
     */
    Student getUserIsExist(String userno, String email);

    /**
     * 根据token查找学生
     * @param token 登录令牌
     * @return 学生信息
     */
    Student findStudentByToken(String token);
} 