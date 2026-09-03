package com.jf.service;

/**
 * 邮件服务接口
 * 用于发送邮件，比如验证码等
 */
public interface EmailService {
    
    /**
     * 发送邮件
     * @param to 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return 是否发送成功
     */
    boolean sendEmail(String to, String subject, String content);
} 