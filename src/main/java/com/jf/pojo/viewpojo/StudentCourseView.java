package com.jf.pojo.viewpojo;

import lombok.Data;
import java.io.Serializable;

/**
 * 学生已选课程视图
 */
@Data
public class StudentCourseView implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer courseId;
    private String courseName;
    private String coursePicture;
    private Integer teacherId;
    private String teacherName;
    private String courseType;
    private Float courseScore;
    private String address;
    private String term;
    private Float usualGrade;
    private Float examGrade;
    private Float allGrade;
    private Integer chooseId;
} 