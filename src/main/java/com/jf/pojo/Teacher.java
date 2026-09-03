package com.jf.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 教师实体类
 */
@Data
public class Teacher implements Serializable {
    private Integer teacherId;
    private String teacherNo;
    private String teacherName;
    private String teacherSex;
    private String teacherPassword;
    private String teacherPhone;
    private String teacherEmail;
    private String teacherRole;
    private Integer majorId;
    private Date firstDatetime;
    private Date lastDatetime;
} 