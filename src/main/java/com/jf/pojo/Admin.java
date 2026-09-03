package com.jf.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 管理员实体类
 */
@Data
public class Admin implements Serializable {
    private Integer adminId;
    private String adminNo;
    private String adminPassword;
    private String adminName;
    private String adminPhone;
    private String adminEmail;
    private String adminRole;
    private Date firstDatetime;
    private Date lastDatetime;
} 