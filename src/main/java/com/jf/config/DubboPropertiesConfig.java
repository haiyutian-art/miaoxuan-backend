package com.jf.config;

import org.apache.dubbo.config.ProtocolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Dubbo 属性配置工厂类
 * 用于确保所有 Dubbo 配置正确初始化，避免空指针异常
 */
@Configuration
public class DubboPropertiesConfig {

    @Value("${dubbo.protocol.name:dubbo}")
    private String protocolName;

    @Value("${dubbo.protocol.port:20880}")
    private int protocolPort;

    /**
     * 提供协议配置
     */
    @Bean
    @Primary
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }
} 