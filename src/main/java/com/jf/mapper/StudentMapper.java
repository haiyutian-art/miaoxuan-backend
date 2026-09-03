package com.jf.mapper;

import com.jf.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 学生数据访问接口
 */
@Mapper
@Repository
public interface StudentMapper {
    
    /**
     * 根据学号查询学生
     */
    Student selectByStudentNo(@Param("studentNo") String studentNo);
    
    /**
     * 更新学生信息
     */
    int updateByPrimaryKeySelective(Student record);
    
    /**
     * 根据Email查询学生
     */
    Student selectByStudentEmail(@Param("studentEmail") String studentEmail);
    
    /**
     * 重置密码
     */
    int updatePassword(@Param("studentNo") String studentNo, @Param("password") String password);
    
    /**
     * 根据学号更新密码
     */
    int updatePasswordByStudentNo(@Param("studentNo") String studentNo, 
                                  @Param("password") String password, 
                                  @Param("updateTime") Date updateTime);
    
    /**
     * 根据学生ID查询密码
     */
    String selectPasswordByStudentId(@Param("studentId") Integer studentId);
    
    /**
     * 根据学号和邮箱查询学生
     */
    Student selectByUserNoAndEmail(@Param("studentNo") String studentNo, 
                                   @Param("studentEmail") String studentEmail);
                                   
    /**
     * 根据主键查询学生
     */
    Student selectByPrimaryKey(@Param("studentId") Integer studentId);
} 