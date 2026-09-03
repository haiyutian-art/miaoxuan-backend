package com.jf.config;

import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * Dubbo 注册中心配置类
 * 专门负责创建和管理 RegistryConfig 实例，避免重复 Bean 定义
 */
@Configuration
public class RegistryConfiguration {

    @Value("${dubbo.registry.address:zookeeper://localhost:2181}")
    private String address;

    @Value("${dubbo.registry.timeout:60000}")
    private int timeout;

    @Value("${dubbo.registry.check:false}")
    private boolean check;

    @Value("${dubbo.registry.register:true}")
    private boolean register;

    /**
     * 创建单个 RegistryConfig 实例
     * 使用 @Primary 确保它是首选的注入实例
     * 使用特定名称避免与其他 Bean 冲突
     */
    @Bean(name = "primaryRegistryConfig")
    @Primary
    public RegistryConfig registryConfig() {
        RegistryConfig config = new RegistryConfig();
        config.setAddress(address);
        config.setTimeout(timeout);
        config.setCheck(check);
        config.setRegister(register);
        
        // 设置参数
        Map<String, String> parameters = new HashMap<>();
        parameters.put("retryTimes", "10");
        parameters.put("retryInterval", "5000");
        config.setParameters(parameters);
        
        System.out.println("创建 RegistryConfig (primaryRegistryConfig): " + config);
        return config;
    }
} 