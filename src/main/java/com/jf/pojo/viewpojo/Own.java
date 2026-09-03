package com.jf.pojo.viewpojo;

import lombok.Data;
import java.io.Serializable;

/**
 * 用户信息视图类
 * 用于前端显示当前登录用户的基本信息
 */
@Data
public class Own implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户编号
     */
    private String userNo;
    
    /**
     * 用户姓名
     */
    private String username;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户角色(student/teacher/admin)
     */
    private String role;
    
    /**
     * 用户ID
     */
    private Integer id;
} 