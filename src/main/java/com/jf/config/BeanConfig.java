package com.jf.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysisReporter;
import org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Map;

/**
 * Bean 配置类
 * 处理可能的循环依赖和 Bean 冲突问题
 * 增强启动诊断功能，显示所有可能的问题
 */
@Configuration
public class BeanConfig {

    @Autowired
    private ApplicationContext applicationContext;
    
    /**
     * 应用启动诊断，报告可能的问题
     */
    @Bean
    public Object startupDiagnostics() {
        System.out.println("\n\n=== 应用启动诊断信息 ===");
        System.out.println("Java 版本: " + System.getProperty("java.version"));
        System.out.println("操作系统: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("ZooKeeper 地址: " + System.getProperty("dubbo.metadata-report.address", "未设置"));
        
        try {
            // 检查关键Bean是否存在
            checkBeanAvailability("dataSource", "数据源");
            checkBeanAvailability("entityManagerFactory", "JPA实体管理器");
            checkBeanAvailability("redisConnectionFactory", "Redis连接工厂");
            checkBeanAvailability("curatorFramework", "ZooKeeper客户端");
        } catch (Exception e) {
            System.out.println("诊断过程中发生错误: " + e.getMessage());
        }
        
        // 显示所有激活的Profile
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        System.out.println("激活的配置文件: " + (activeProfiles.length == 0 ? "default" : String.join(", ", activeProfiles)));
        
        System.out.println("=== 应用启动诊断完成 ===\n\n");
        return new Object();
    }
    
    /**
     * 检查Bean是否可用
     */
    private void checkBeanAvailability(String beanName, String description) {
        boolean available = applicationContext.containsBean(beanName);
        System.out.println(description + " [" + beanName + "]: " + (available ? "可用" : "不可用"));
    }
    
    /**
     * 允许循环依赖
     */
    @Bean
    public static DefaultListableBeanFactory enableCircularReferences(
            DefaultListableBeanFactory beanFactory) {
        beanFactory.setAllowCircularReferences(true);
        System.out.println("已启用循环依赖支持");
        return beanFactory;
    }
    
    /**
     * 自定义故障分析报告器，收集所有错误
     */
    @Bean
    public FailureAnalysisReporter failureAnalysisReporter() {
        return new LoggingFailureAnalysisReporter();
    }
    
    /**
     * 配置属性绑定后处理器，确保所有配置问题都被记录
     */
    @Bean
    public static ConfigurationPropertiesBindingPostProcessor configurationPropertiesBindingPostProcessor() {
        ConfigurationPropertiesBindingPostProcessor processor = new ConfigurationPropertiesBindingPostProcessor();
        return processor;
    }
} 