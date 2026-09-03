package com.jf.config;

import com.jf.pojo.viewpojo.CourseView;
import com.jf.service.InitCourseService;
import com.jf.utils.RedisOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存预加载监听器
 * 系统启动时将课程信息预加载到Redis中，提高访问性能
 */
@Lazy
@Component
public class LoadListener implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(LoadListener.class);
    private static final int MAX_RETRY_TIMES = 3;

    @Autowired
    private InitCourseService initCourseService;
    
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 初始化方法，加载课程信息到Redis
     */
    public void init() {
        int retryCount = 0;
        boolean success = false;
        
        while (!success && retryCount < MAX_RETRY_TIMES) {
            try {
                // 清除旧缓存
                redisOperator.removeCourseView();
                
                // 加载课程列表
                List<CourseView> courseList = initCourseService.initCourseView();
                
                if (CollectionUtils.isEmpty(courseList)) {
                    logger.warn("未能获取到课程数据，重试中... ({}/{})", retryCount + 1, MAX_RETRY_TIMES);
                    retryCount++;
                    TimeUnit.SECONDS.sleep(3); // 等待3秒后重试
                    continue;
                }
                
                // 将课程缓存到Redis
                for (CourseView course : courseList) {
                    redisOperator.setCourseViews(course);
                }
                
                // 打印缓存状态
                Map<Object, Object> courseViews = redisOperator.getCourseViews();
                logger.info("课程缓存完毕，共缓存{}门课程", courseViews.size());
                success = true;
                
            } catch (Exception e) {
                logger.error("课程缓存失败，准备重试 ({}/{}): {}", retryCount + 1, MAX_RETRY_TIMES, e.getMessage());
                retryCount++;
                
                try {
                    TimeUnit.SECONDS.sleep(5); // 等待5秒后重试
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        if (!success) {
            logger.error("经过{}次重试，课程缓存仍然失败，将在运行时按需加载", MAX_RETRY_TIMES);
        }
    }

    /**
     * 销毁方法，清除Redis缓存
     */
    @PreDestroy
    public void destroy() {
        try {
            redisOperator.removeCourseView();
            logger.info("已销毁课程缓存");
        } catch (Exception e) {
            logger.error("销毁课程缓存失败: {}", e.getMessage());
        }
    }
    
    /**
     * 实现CommandLineRunner接口，应用启动时自动执行
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("开始初始化课程缓存...");
        init();
    }
} 