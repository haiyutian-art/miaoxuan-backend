package com.jf.service;

import com.jf.pojo.Course;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.pojo.viewpojo.StudentCourseView;

import java.util.List;

/**
 * 课程服务接口
 * 用于提供课程查询相关的方法
 */
public interface CourseService {

    /**
     * 获取所有课程
     * @return 课程列表
     */
    List<Course> getCourse();

    /**
     * 获取课程视图列表
     * @param term 学期
     * @param studentId 学生ID
     * @return 课程视图列表
     */
    List<CourseView> getCourseView(String term, Integer studentId);

    /**
     * 获取学生已选课程
     * @param studentId 学生ID
     * @param term 学期
     * @return 学生已选课程视图列表
     */
    List<StudentCourseView> getMyCourse(Integer studentId, String term);

    /**
     * 根据课程类型获取课程
     * @param type 课程类型
     * @param studentId 学生ID
     * @param term 学期
     * @return 课程视图列表
     */
    List<CourseView> getCourseViewByType(String type, Integer studentId, String term);

    /**
     * 根据专业获取课程
     * @param major 专业ID
     * @param studentId 学生ID
     * @param term 学期
     * @return 课程视图列表
     */
    List<CourseView> getCourseByMajor(Integer major, Integer studentId, String term);

    /**
     * 根据课程类型和专业获取课程
     * @param type 课程类型
     * @param major 专业ID
     * @param studentId 学生ID
     * @param term 学期
     * @return 课程视图列表
     */
    List<CourseView> getCourseByTypeAndMajor(String type, Integer major, Integer studentId, String term);

    /**
     * 根据课程名称搜索课程
     * @param courseName 课程名称关键字
     * @param studentId 学生ID
     * @param term 学期
     * @return 课程视图列表
     */
    List<CourseView> getCourseByCourseName(String courseName, Integer studentId, String term);
    
    /**
     * 根据教师ID获取课程
     * @param teacherId 教师ID
     * @return 课程视图列表
     */
    List<CourseView> getCourseByTeacherId(Integer teacherId);
} 