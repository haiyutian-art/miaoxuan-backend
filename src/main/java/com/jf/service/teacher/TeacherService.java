package com.jf.service.teacher;

import com.jf.pojo.Teacher;

/**
 * 教师服务接口
 * 处理教师相关功能
 */
public interface TeacherService {

    /**
     * 根据教师号和邮箱查询教师
     * @param userno 教师号
     * @param email 邮箱
     * @return 教师信息
     */
    Teacher getUserIsExist(String userno, String email);

    /**
     * 获取教师密码
     * @param teacherId 教师ID
     * @return 密码
     */
    String getPassword(Integer teacherId);

    /**
     * 更新教师密码
     * @param teacherNo 教师号
     * @param newpass 新密码
     * @return 影响行数
     */
    int updatePassword(String teacherNo, String newpass);
    
    /**
     * 根据token查找教师
     * @param token 登录令牌
     * @return 教师信息
     */
    Teacher findTeacherByToken(String token);
} 