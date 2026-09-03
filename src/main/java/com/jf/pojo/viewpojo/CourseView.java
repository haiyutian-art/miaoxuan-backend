package com.jf.pojo.viewpojo;

import lombok.Data;
import java.io.Serializable;

/**
 * 课程视图实体
 */
@Data
public class CourseView implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer courseId;
    private String courseName;
    private String coursePicture;
    private Integer majorId;
    private Integer teacherId;
    private String teacherName;
    private String courseType;
    private Float courseScore;
    private Integer stock;
    private String address;
    private String term;
    private Integer number;
    private String description;
    private String majorName;
    private String status; // 0: 未选, 1: 已选
} 