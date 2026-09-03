package com.jf.service;

import com.jf.common.ServerResponse;
import com.jf.pojo.Choose;
import com.jf.pojo.viewpojo.ChooseGrade;

import java.util.Date;
import java.util.List;

/**
 * 选课服务接口
 */
public interface ChooseService {

    /**
     * 学生选课
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param firstDatetime 创建时间
     * @param lastDatetime 更新时间
     * @param term 学期
     * @return 选课结果
     */
    ServerResponse<Object> chooseCourse(Integer studentId, Integer courseId, Date firstDatetime, Date lastDatetime, String term);

    /**
     * 学生退课
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @param firstDatetime 创建时间
     * @param lastDatetime 更新时间
     * @return 退课结果
     */
    ServerResponse<Object> deleteCourse(Integer studentId, Integer courseId, Date firstDatetime, Date lastDatetime);

    /**
     * 获取教师课程的选课学生列表
     * @param courseId 课程ID
     * @return 选课学生列表
     */
    List<Choose> getTeacherCourse(Integer courseId);
    
    /**
     * 获取学生成绩列表
     * @param courseId 课程ID
     * @return 学生成绩列表
     */
    List<ChooseGrade> getStudentsGradeList(Integer courseId);
    
    /**
     * 更新学生成绩
     * @param chooseId 选课记录ID
     * @param usualGrade 平时成绩
     * @param examGrade 考试成绩
     * @param allGrade 总成绩
     * @param lastDatetime 更新时间
     * @return 更新结果
     */
    ServerResponse<String> updateGrade(Integer chooseId, Float usualGrade, Float examGrade, Float allGrade, Date lastDatetime);
} 