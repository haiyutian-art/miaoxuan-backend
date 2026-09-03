package com.jf.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 选课记录实体类
 */
@Data
@Table(name = "choose")
public class Choose implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choose_id")
    private Integer chooseId;
    
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "course_id")
    private Integer courseId;
    
    @Column(name = "usual_grade")
    private Float usualGrade;
    
    @Column(name = "exam_grade")
    private Float examGrade;
    
    @Column(name = "all_grade")
    private Float allGrade;
    
    @Column(name = "my_term")
    private String myTerm;
    
    @Column(name = "first_datetime")
    private Date firstDatetime;
    
    @Column(name = "last_datetime")
    private Date lastDatetime;
} 