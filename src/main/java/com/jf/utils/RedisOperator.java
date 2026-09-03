package com.jf.utils;

import com.jf.pojo.viewpojo.CourseView;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 * 封装了对课程视图对象的Redis操作
 */
@Component
public class RedisOperator {

    /**
     * 课程视图缓存的键
     */
    private static final String COURSE_VIEW_KEY = "courseview";
    
    /**
     * 课程缓存过期时间（小时）
     */
    private static final int CACHE_EXPIRE_HOURS = 24;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 将课程视图对象保存到Redis缓存
     * @param course 课程视图对象
     */
    public void setCourseViews(CourseView course) {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        // 将Integer类型的courseId转换为String类型
        String courseIdStr = String.valueOf(course.getCourseId());
        hashOperations.put(courseIdStr, course);
        // 设置过期时间
        redisTemplate.expire(COURSE_VIEW_KEY, CACHE_EXPIRE_HOURS, TimeUnit.HOURS);
    }

    /**
     * 获取所有缓存的课程视图对象
     * @return 课程ID到课程视图对象的映射
     */
    public Map<Object, Object> getCourseViews() {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        return hashOperations.entries();
    }

    /**
     * 根据课程ID获取课程视图对象
     * @param courseId 课程ID
     * @return 课程视图对象
     */
    public CourseView getCourseViewById(Integer courseId) {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        // 将Integer类型的courseId转换为String类型
        String courseIdStr = String.valueOf(courseId);
        return (CourseView) hashOperations.get(courseIdStr);
    }

    /**
     * 更新课程视图对象
     * @param courseId 课程ID
     * @param course 更新后的课程视图对象
     */
    public void updateCourseView(Integer courseId, CourseView course) {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        // 将Integer类型的courseId转换为String类型
        String courseIdStr = String.valueOf(courseId);
        hashOperations.put(courseIdStr, course);
    }

    /**
     * 删除课程视图缓存
     * @param courseId 课程ID
     */
    public void deleteCourseView(Integer courseId) {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        // 将Integer类型的courseId转换为String类型
        String courseIdStr = String.valueOf(courseId);
        hashOperations.delete(courseIdStr);
    }

    /**
     * 清除所有课程视图缓存
     */
    public void removeCourseView() {
        redisTemplate.delete(COURSE_VIEW_KEY);
    }

    /**
     * 检查课程是否在缓存中
     * @param courseId 课程ID
     * @return 是否存在
     */
    public boolean courseViewExists(Integer courseId) {
        BoundHashOperations<String, Object, Object> hashOperations = redisTemplate.boundHashOps(COURSE_VIEW_KEY);
        // 将Integer类型的courseId转换为String类型
        String courseIdStr = String.valueOf(courseId);
        return hashOperations.hasKey(courseIdStr);
    }
} 