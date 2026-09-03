package com.jf.mapper;

import com.jf.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 教师数据访问接口
 */
@Mapper
@Repository
public interface TeacherMapper {
    
    /**
     * 根据工号查询教师信息
     */
    Teacher selectByTeacherNo(@Param("teacherNo") String teacherNo);
    
    /**
     * 更新教师信息
     */
    int updateByPrimaryKeySelective(Teacher record);
    
    /**
     * 根据Email查询教师
     */
    Teacher selectByTeacherEmail(@Param("teacherEmail") String teacherEmail);
    
    /**
     * 重置密码
     */
    int updatePassword(@Param("teacherNo") String teacherNo, @Param("password") String password);
    
    /**
     * 根据教师ID查询密码
     */
    String selectPasswordByTeacherId(@Param("teacherId") Integer teacherId);
    
    /**
     * 根据工号和邮箱查询教师
     */
    Teacher selectByUserNoAndEmail(@Param("teacherNo") String teacherNo, @Param("teacherEmail") String teacherEmail);
    
    /**
     * 根据教师工号更新密码
     */
    int updatePasswordByTeacherNo(@Param("teacherNo") String teacherNo, 
                                 @Param("password") String password, 
                                 @Param("updateTime") Date updateTime);
} 