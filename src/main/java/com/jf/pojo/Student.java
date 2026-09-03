package com.jf.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生实体类
 */
@Data
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "student_no")
    private String studentNo;
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "student_sex")
    private String studentSex;
    
    @Column(name = "student_password")
    private String studentPassword;
    
    @Column(name = "student_phone")
    private String studentPhone;
    
    @Column(name = "student_email")
    private String studentEmail;
    
    @Column(name = "student_major")
    private Integer majorId;
    
    @Column(name = "student_role")
    private String studentRole;
    
    @Column(name = "first_datetime")
    private Date firstDatetime;
    
    @Column(name = "last_datetime")
    private Date lastDatetime;

    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.studentPassword = password;
    }
} 