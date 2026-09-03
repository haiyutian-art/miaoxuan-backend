package com.jf.service.teacher;

import com.jf.pojo.viewpojo.ChooseGrade;
import com.jf.pojo.viewpojo.CourseView;
import java.util.List;

/**
 * 教师课程服务接口
 * 处理教师课程相关功能
 */
public interface TeacherCourseService {

    /**
     * 获取教师课程列表
     * @param teacherId 教师ID
     * @return 课程视图列表
     */
    List<CourseView> getCourse(Integer teacherId);

    /**
     * 根据课程ID获取选课学生列表
     * @param courseId 课程ID
     * @return 选课学生成绩视图列表
     */
    List<ChooseGrade> selectByCourseId(Integer courseId);

    /**
     * 根据选课ID获取选课记录详情
     * @param chooseId 选课ID
     * @return 选课成绩视图
     */
    ChooseGrade getChooseByChooseId(Integer chooseId);
} 