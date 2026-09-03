package com.jf.service;

import com.jf.pojo.viewpojo.CourseView;

import java.util.List;

/**
 * 课程初始化服务接口
 * 用于系统启动时加载课程信息
 */
public interface InitCourseService {
    
    /**
     * 初始化课程视图列表
     * @return 课程视图列表
     */
    List<CourseView> initCourseView();
} 