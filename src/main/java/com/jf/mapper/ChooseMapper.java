package com.jf.mapper;

import com.jf.pojo.Choose;
import com.jf.pojo.viewpojo.ChooseGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 选课记录数据访问接口
 */
@Mapper
@Repository
public interface ChooseMapper {
    /**
     * 删除选课记录
     */
    int deleteByPrimaryKey(Integer chooseId);

    /**
     * 新增选课记录
     */
    int insert(Choose record);

    /**
     * 新增选课记录（可选属性）
     */
    int insertSelective(Choose record);

    /**
     * 根据ID查询选课记录
     */
    Choose selectByPrimaryKey(Integer chooseId);

    /**
     * 更新选课记录（可选属性）
     */
    int updateByPrimaryKeySelective(Choose record);

    /**
     * 更新选课记录
     */
    int updateByPrimaryKey(Choose record);

    /**
     * 选课操作
     */
    int chooseCourse(Choose record);

    /**
     * 根据学生ID和学期查询选课记录
     */
    List<Choose> selectByStudentId(@Param("studentId") Integer studentId, @Param("term") String term);

    /**
     * 根据课程ID和学生ID查询选课记录
     */
    Choose selectByCourseIdAndStudentId(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    /**
     * 根据课程ID和学生ID删除选课记录
     */
    int deleteByCourseIdAndStudentId(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    /**
     * 根据课程ID查询选课记录
     */
    List<Choose> selectByCourseId(Integer courseId);

    /**
     * 更新成绩
     */
    int updateGradeByChooseId(@Param("chooseId") Integer chooseId,
                              @Param("usualGrade") Float usualGrade,
                              @Param("examGrade") Float examGrade,
                              @Param("allGrade") Float allGrade,
                              @Param("lastDatetime") Date lastDatetime);
    
    /**
     * 获取学生成绩列表
     */
    List<ChooseGrade> getStudentsGradeList(Integer courseId);
}
