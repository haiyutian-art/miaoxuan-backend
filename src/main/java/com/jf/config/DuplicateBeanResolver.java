package com.jf.config;

import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 用于处理重复 Bean 定义的解析器
 * 优先级设为最高，确保在其他Bean创建前执行
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DuplicateBeanResolver implements BeanFactoryPostProcessor {

    // 需要保留的Bean名称
    private static final String PRIMARY_REGISTRY_BEAN = "primaryRegistryConfig";
    
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanFactory;
        
        // 设置允许 Bean 定义覆盖
        factory.setAllowBeanDefinitionOverriding(true);
        
        // 打印所有已经注册的 Bean 定义
        System.out.println("\n==== Bean 定义检查开始 ====");
        for (String beanName : factory.getBeanDefinitionNames()) {
            if (beanName.contains("Registry") || beanName.contains("dubbo")) {
                System.out.println("检测到 Bean 定义: " + beanName + " -> " + 
                    factory.getBeanDefinition(beanName).getBeanClassName());
            }
        }
        
        // 强制移除可能导致冲突的Bean定义
        removeConflictingBeans(factory, RegistryConfig.class, PRIMARY_REGISTRY_BEAN, 
            "_dubboRegistryConfig", "registryConfig", "_dubboInitializerRegistryConfig");
        
        System.out.println("==== Bean 定义检查结束 ====\n");
    }
    
    /**
     * 移除可能导致冲突的Bean定义
     * 
     * @param factory Bean工厂
     * @param beanClass 要检查的Bean类型
     * @param primaryBeanName 要保留的主要Bean名称
     * @param conflictingPrefixes 可能冲突的Bean名称前缀
     */
    private <T> void removeConflictingBeans(DefaultListableBeanFactory factory, 
                                           Class<T> beanClass, 
                                           String primaryBeanName,
                                           String... conflictingPrefixes) {
        try {
            // 获取特定类型的所有Bean
            String[] beanNames = factory.getBeanNamesForType(beanClass, true, false);
            
            if (beanNames.length > 1) {
                System.out.println("发现多个 " + beanClass.getSimpleName() + " Bean: " + 
                                  Arrays.toString(beanNames));
                
                // 确保主要Bean被保留
                boolean primaryBeanFound = false;
                for (String beanName : beanNames) {
                    if (beanName.equals(primaryBeanName)) {
                        primaryBeanFound = true;
                        System.out.println("保留主要Bean: " + primaryBeanName);
                        break;
                    }
                }
                
                // 如果找不到主要Bean，则保留第一个
                String beanToKeep = primaryBeanFound ? primaryBeanName : beanNames[0];
                
                // 移除其他Bean定义
                for (String beanName : beanNames) {
                    if (!beanName.equals(beanToKeep)) {
                        try {
                            // 检查是否为冲突前缀
                            boolean isConflicting = false;
                            for (String prefix : conflictingPrefixes) {
                                if (beanName.contains(prefix)) {
                                    isConflicting = true;
                                    break;
                                }
                            }
                            
                            if (isConflicting || beanNames.length > 2) {
                                System.out.println("正在移除冲突的Bean定义: " + beanName);
                                factory.removeBeanDefinition(beanName);
                            }
                        } catch (Exception e) {
                            System.err.println("无法移除Bean定义 " + beanName + ": " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("处理 " + beanClass.getSimpleName() + " Bean冲突时出错: " + e.getMessage());
        }
    }
} 