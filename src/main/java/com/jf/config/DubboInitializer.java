package com.jf.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Dubbo 初始化器
 * 在应用上下文创建前初始化 Dubbo 配置
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DubboInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("初始化 Dubbo 配置...");
        
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        
        // 确保 Dubbo 配置在系统属性中存在，优先级高于配置文件
        ensureProperty(environment, "dubbo.registry.address", "zookeeper://localhost:2181");
        ensureProperty(environment, "dubbo.metadata-report.address", "zookeeper://localhost:2181");
        ensureProperty(environment, "dubbo.config-center.address", "zookeeper://localhost:2181");
        ensureProperty(environment, "dubbo.application.name", "uniapp-course-backend");
        ensureProperty(environment, "dubbo.registry.file", System.getProperty("user.home") + "/dubbo-cache/dubbo.cache");
        
        // 设置关键系统属性
        System.setProperty("dubbo.registry.check", "false");
        System.setProperty("dubbo.registry.timeout", "60000");
        System.setProperty("dubbo.registry.parameters.retryTimes", "10");
        System.setProperty("dubbo.registry.parameters.retryInterval", "5000");
        System.setProperty("dubbo.consumer.check", "false");
        System.setProperty("dubbo.consumer.timeout", "60000");
        
        // 禁用重复注册
        System.setProperty("dubbo.registry.simplified", "true");
        System.setProperty("dubbo.service.register", "false");
        System.setProperty("dubbo.application.register-mode", "instance");
        
        System.out.println("Dubbo 系统属性初始化完成");
    }
    
    private void ensureProperty(Environment environment, String key, String defaultValue) {
        // 如果环境变量或系统属性中不存在该属性，则设置系统属性
        if (environment.getProperty(key) == null) {
            System.setProperty(key, defaultValue);
            System.out.println("设置系统属性: " + key + "=" + defaultValue);
        } else {
            System.out.println("系统属性已存在: " + key + "=" + environment.getProperty(key));
        }
    }
} 