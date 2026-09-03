package com.jf.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class CuratorDiscoveryConfig {

    @Autowired
    private CuratorFramework curatorFramework;
    
    /**
     * 提供 Curator 服务发现功能
     */
    @Bean(initMethod = "start", destroyMethod = "close")
    public ServiceDiscovery<Object> serviceDiscovery() throws Exception {
        JsonInstanceSerializer<Object> serializer = new JsonInstanceSerializer<>(Object.class);
        
        return ServiceDiscoveryBuilder.builder(Object.class)
                .client(curatorFramework)
                .basePath("/services")
                .serializer(serializer)
                .build();
    }
} 