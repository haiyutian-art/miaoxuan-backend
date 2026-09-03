package com.jf.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程实体类
 */
@Data
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "course_name")
    private String courseName;
    
    @Column(name = "course_picture")
    private String coursePicture;
    
    @Column(name = "major_id")
    private Integer majorId;
    
    @Column(name = "teacher_id")
    private Integer teacherId;
    
    @Column(name = "course_type")
    private String courseType;
    
    @Column(name = "course_score")
    private Float courseScore;
    
    @Column(name = "stock")
    private Integer stock;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "term")
    private String term;
    
    @Column(name = "number")
    private Integer number;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "first_datetime")
    private Date firstDatetime;
    
    @Column(name = "last_datetime")
    private Date lastDatetime;
} 