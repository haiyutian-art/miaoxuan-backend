package com.jf.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo 配置类
 * 用于配置 Dubbo 3.x 元数据
 */
@Configuration
public class DubboConfig {

    @Value("${dubbo.metadata-report.address:zookeeper://localhost:2181}")
    private String metadataReportAddress;
    
    @Value("${dubbo.application.name:uniapp-course-backend}")
    private String applicationName;
    
    /**
     * 配置应用程序信息
     */
    @Bean(name = "primaryApplicationConfig")
    public ApplicationConfig applicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName(applicationName);
        config.setQosEnable(false);
        config.setLogger("slf4j");
        System.out.println("创建 ApplicationConfig (primaryApplicationConfig): " + config);
        return config;
    }

    /**
     * 配置元数据报告
     */
    @Bean(name = "primaryMetadataReportConfig")
    public MetadataReportConfig metadataReportConfig() {
        MetadataReportConfig config = new MetadataReportConfig();
        config.setAddress(metadataReportAddress);
        config.setRetryTimes(5);
        config.setRetryPeriod(2000);
        config.setCycleReport(false);
        System.out.println("创建 MetadataReportConfig (primaryMetadataReportConfig): " + config);
        return config;
    }
} 