package com.jf.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {
    
    @Value("${dubbo.registry.address}")
    private String zookeeperAddress;
    
    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        // 提取 zookeeper 地址
        String zkAddress = zookeeperAddress.replace("zookeeper://", "");
        
        // 重试策略，初始休眠1秒，最多重试5次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        
        // 创建 CuratorFramework 实例
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(30000)  // 增加连接超时时间
                .retryPolicy(retryPolicy)
                .build();
        
        // 不在这里调用 client.start()，而是通过 initMethod 启动
        return client;
    }
} 