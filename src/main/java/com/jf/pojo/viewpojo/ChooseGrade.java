package com.jf.pojo.viewpojo;

import lombok.Data;
import java.io.Serializable;

/**
 * 学生成绩视图
 */
@Data
public class ChooseGrade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer chooseId;
    private Integer courseId;
    private Integer studentId;
    private String studentNo;
    private String studentName;
    private String majorName;
    private String courseName;
    private Float usualGrade;
    private Float examGrade;
    private Float allGrade;
} 