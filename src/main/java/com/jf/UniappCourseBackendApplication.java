package com.jf;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * uniapp课程选择系统后端应用
 * 整合了原有的选课系统功能，提供给uniapp前端调用的API
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jf.mapper")
@EnableDubbo(scanBasePackages = "com.jf.service")
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class UniappCourseBackendApplication {

    public static void main(String[] args) {
        try {
            // 必须在创建 SpringApplication 之前设置这些属性
            // 设置Dubbo元数据地址系统属性
            String zkAddress = System.getenv("ZOOKEEPER_ADDRESS");
            if (zkAddress == null || zkAddress.isEmpty()) {
                zkAddress = "localhost:2181"; // 默认值
            }
            
            System.out.println("使用ZooKeeper地址: zookeeper://" + zkAddress);
            
            // 设置关键系统属性
            System.setProperty("dubbo.registry.address", "zookeeper://" + zkAddress);
            System.setProperty("dubbo.metadata-report.address", "zookeeper://" + zkAddress);
            System.setProperty("dubbo.config-center.address", "zookeeper://" + zkAddress);
            System.setProperty("dubbo.registry.file", System.getProperty("user.home") + "/dubbo-cache/dubbo.cache");
            System.setProperty("dubbo.application.qos-enable", "false");
            
            // 设置配置类早期初始化标志
            System.setProperty("spring.main.allow-bean-definition-overriding", "true");
            System.setProperty("spring.main.allow-circular-references", "true");
            
            // 配置 Dubbo 不自动注册或使用特定 Bean
            System.setProperty("dubbo.config.multiple", "false");
            System.setProperty("dubbo.application.register-mode", "instance");
            System.setProperty("dubbo.registry.simplified", "true");
            
            // 禁用Dubbo自动配置
            System.setProperty("spring.autoconfigure.exclude", 
                "org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration");
            System.setProperty("dubbo.scan.base-packages", "");
            
            // 确保使用自定义配置类
            System.setProperty("dubbo.registry.id", "primaryRegistryConfig");
            System.setProperty("dubbo.application.id", "primaryApplicationConfig");
            System.setProperty("dubbo.metadata-report.id", "primaryMetadataReportConfig");
            
            // 启用完整错误收集
            System.setProperty("spring.boot.enableautoconfiguration", "true");
            System.setProperty("spring.boot.fail-fast", "false");
            
            // 创建应用程序实例
            SpringApplication application = new SpringApplication(UniappCourseBackendApplication.class);
            
            // 添加 Dubbo 初始化器
            application.addInitializers(new com.jf.config.DubboInitializer());
            
            // 添加额外的属性
            Map<String, Object> props = new HashMap<>();
            props.put("dubbo.registry.use-as-config-center", "false");
            props.put("dubbo.registry.use-as-metadata-center", "false");
            props.put("dubbo.consumer.validation", "false");
            application.setDefaultProperties(props);
            
            // 设置显示所有启动错误
            application.setRegisterShutdownHook(true);
            application.setLogStartupInfo(true);
            
            // 添加启动Banner以便查看所有错误
            application.setBannerMode(org.springframework.boot.Banner.Mode.CONSOLE);
            
            // 禁用快速失败，收集所有错误
            application.setAddCommandLineProperties(true);
            application.setAddConversionService(true);
            
            // 启动应用并捕获异常
            try {
                ConfigurableApplicationContext context = application.run(args);
                System.out.println("应用已成功启动！");
            } catch (Exception e) {
                System.err.println("\n\n=== 应用启动失败，收集到以下错误 ===");
                e.printStackTrace();
                
                // 提取所有嵌套异常并显示
                Throwable cause = e.getCause();
                int level = 1;
                while (cause != null) {
                    System.err.println("\n[错误 #" + level + "] " + cause.getClass().getName() + ": " + cause.getMessage());
                    cause = cause.getCause();
                    level++;
                }
                System.err.println("\n=== 错误收集完成 ===\n\n");
            }
        } catch (Exception e) {
            System.err.println("应用启动前发生严重错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 