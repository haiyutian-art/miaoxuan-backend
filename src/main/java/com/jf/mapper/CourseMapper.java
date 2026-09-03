package com.jf.mapper;

import com.jf.pojo.Course;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.pojo.viewpojo.StudentCourseView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 课程数据访问接口
 */
@Mapper
@Repository
public interface CourseMapper {
    
    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Integer courseId);

    /**
     * 插入课程
     */
    int insert(Course record);

    /**
     * 选择性插入课程
     */
    int insertSelective(Course record);

    /**
     * 根据主键查询
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     * 选择性更新
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 更新课程
     */
    int updateByPrimaryKey(Course record);

    /**
     * 获取所有课程视图
     */
    List<CourseView> getCourseView(@Param("term") String term, @Param("studentId") Integer studentId);

    /**
     * 根据类型获取课程视图
     */
    List<CourseView> getCourseViewByType(@Param("type") String type, @Param("studentId") Integer studentId, @Param("term") String term);

    /**
     * 根据专业获取课程
     */
    List<CourseView> getCourseByMajor(@Param("major") Integer major, @Param("studentId") Integer studentId, @Param("term") String term);

    /**
     * 根据类型和专业获取课程
     */
    List<CourseView> getCourseByTypeAndMajor(@Param("type") String type, @Param("major") Integer major, @Param("studentId") Integer studentId, @Param("term") String term);

    /**
     * 根据课程名查询课程
     */
    List<CourseView> getCourseByCourseName(@Param("courseName") String courseName, @Param("studentId") Integer studentId, @Param("term") String term);
    
    /**
     * 根据学生ID获取已选课程
     */
    List<StudentCourseView> getMyCourse(@Param("studentId") Integer studentId, @Param("term") String term);

    /**
     * 更新课程剩余名额（选课）
     */
    int UpdateCourseToChooseCourse(@Param("courseId") Integer courseId, @Param("lastDatetime") Date lastDatetime);

    /**
     * 更新课程剩余名额（退课）
     */
    int UpdateCourseToDeleteCourse(@Param("courseId") Integer courseId, @Param("lastDatetime") Date lastDatetime);

    /**
     * 根据教师ID获取课程
     */
    List<CourseView> getCourseByTeacherId(Integer teacherId);
    
    /**
     * 初始化课程视图
     */
    List<CourseView> initCourseView();
} 