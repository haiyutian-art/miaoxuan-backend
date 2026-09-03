package com.jf.pojo;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 专业实体类
 */
@Data
@Table(name = "major")
public class Major implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private Integer majorId;
    
    @Column(name = "major_name")
    private String majorName;
    
    @Column(name = "first_datetime")
    private Date firstDatetime;
    
    @Column(name = "last_datetime")
    private Date lastDatetime;
} 