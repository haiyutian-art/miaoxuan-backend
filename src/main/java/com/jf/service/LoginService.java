package com.jf.service;

import com.jf.pojo.Admin;
import com.jf.pojo.Student;
import com.jf.pojo.Teacher;

/**
 * 登录服务接口
 * 用于处理用户认证相关功能
 */
public interface LoginService {

    /**
     * 根据学号查询学生信息
     * @param studentNo 学号
     * @return 学生信息
     */
    Student selectStudentByStudentNo(String studentNo);

    /**
     * 根据管理员号查询管理员信息
     * @param adminNo 管理员号
     * @return 管理员信息
     */
    Admin selectAdminByAdminNo(String adminNo);

    /**
     * 根据教师号查询教师信息
     * @param teacherNo 教师号
     * @return 教师信息
     */
    Teacher selectTeacherByTeacherNo(String teacherNo);
} 