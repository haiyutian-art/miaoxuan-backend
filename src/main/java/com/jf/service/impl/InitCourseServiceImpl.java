package com.jf.service.impl;

import com.jf.mapper.CourseMapper;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.service.InitCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程初始化服务实现类
 */
@Service
public class InitCourseServiceImpl implements InitCourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 初始化课程视图列表
     * 从数据库中获取所有课程信息
     * @return 课程视图列表
     */
    @Override
    public List<CourseView> initCourseView() {
        // 查询所有课程及其相关信息
        return courseMapper.initCourseView();
    }
} 