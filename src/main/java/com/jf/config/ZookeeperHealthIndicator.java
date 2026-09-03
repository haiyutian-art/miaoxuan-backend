package com.jf.config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ZookeeperHealthIndicator implements HealthIndicator {
    
    private final CuratorFramework curatorFramework;
    
    public ZookeeperHealthIndicator(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }
    
    @Override
    public Health health() {
        try {
            if (curatorFramework.getZookeeperClient().isConnected()) {
                return Health.up()
                        .withDetail("state", curatorFramework.getState())
                        .withDetail("connected", true)
                        .build();
            } else {
                return Health.down()
                        .withDetail("state", curatorFramework.getState())
                        .withDetail("connected", false)
                        .build();
            }
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
} 