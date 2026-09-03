package com.jf.service.impl;

import com.jf.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件服务实现类
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单文本邮件
     * @param to 收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return 是否发送成功
     */
    @Override
    public boolean sendEmail(String to, String subject, String content) {
        // 创建邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        
        // 设置发件人
        message.setFrom(from);
        // 设置收件人
        message.setTo(to);
        // 设置主题
        message.setSubject(subject);
        // 设置内容
        message.setText(content);
        
        try {
            // 发送邮件
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 