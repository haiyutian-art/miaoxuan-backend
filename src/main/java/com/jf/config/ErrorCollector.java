package com.jf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 应用启动错误收集器
 * 用于收集所有启动过程中的错误，而不是遇到一个就停止
 */
@Component
public class ErrorCollector implements BeanPostProcessor, 
        ApplicationListener<ApplicationFailedEvent> {
    
    private static final Logger logger = LoggerFactory.getLogger(ErrorCollector.class);
    private final List<Throwable> errors = new ArrayList<>();
    private final AtomicBoolean errorReported = new AtomicBoolean(false);
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            return bean;
        } catch (Exception e) {
            // 收集Bean初始化错误
            logger.error("Bean [" + beanName + "] 初始化失败: " + e.getMessage(), e);
            errors.add(e);
            // 返回null允许Spring继续处理，而不是中止
            return bean;
        }
    }
    
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        if (errorReported.compareAndSet(false, true)) {
            Throwable error = event.getException();
            logger.error("应用启动失败: " + error.getMessage(), error);
            errors.add(error);
            
            // 打印所有收集到的错误
            printAllErrors();
        }
    }
    
    /**
     * 打印所有收集到的错误
     */
    private void printAllErrors() {
        if (!errors.isEmpty()) {
            logger.error("\n\n============ 启动过程中收集到 " + errors.size() + " 个错误 ============");
            for (int i = 0; i < errors.size(); i++) {
                Throwable error = errors.get(i);
                logger.error("错误 #" + (i+1) + ": " + error.getClass().getName() + ": " + error.getMessage(), error);
            }
            logger.error("============ 错误收集结束 ============\n\n");
        }
    }
} 