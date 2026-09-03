package com.jf.service.impl;

import com.jf.config.LoadListener;
import com.jf.mapper.ChooseMapper;
import com.jf.mapper.CourseMapper;
import com.jf.mapper.MajorMapper;
import com.jf.pojo.Choose;
import com.jf.pojo.Course;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.pojo.viewpojo.StudentCourseView;
import com.jf.service.CourseService;
import com.jf.utils.RedisOperator;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程服务实现类
 * 添加条件化注解，只有在dubbo.application.enabled=true时才启用Dubbo服务
 */
@Service
@Component
@ConditionalOnProperty(name = "dubbo.application.enabled", havingValue = "true", matchIfMissing = true)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private ChooseMapper chooseMapper;
    
    @Autowired
    private MajorMapper majorMapper;
    
    @Autowired
    private RedisOperator redisOperator;
    
    @Autowired
    private LoadListener loadListener;

    @Override
    public List<Course> getCourse() {
        return null; // 暂未实现，后续可根据需求添加
    }

    @Override
    public List<CourseView> getCourseView(String term, Integer studentId) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 获取学生已选课程
        List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
        
        // 过滤获取指定学期的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            if (((CourseView) entry.getValue()).getTerm().equals(term)) {
                courseViews.add(((CourseView) entry.getValue()));
            }
        });
        
        // 标记已选课程
        signSelectedCourse(chooseList, courseViews);
        return courseViews;
    }

    @Override
    public List<StudentCourseView> getMyCourse(Integer studentId, String term) {
        return courseMapper.getMyCourse(studentId, term);
    }

    @Override
    public List<CourseView> getCourseViewByType(String type, Integer studentId, String term) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 获取学生已选课程
        List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
        
        // 过滤获取指定类型和学期的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            CourseView courseView = (CourseView) entry.getValue();
            if (courseView.getCourseType().equals(type) && courseView.getTerm().equals(term)) {
                courseViews.add(courseView);
            }
        });
        
        // 标记已选课程
        signSelectedCourse(chooseList, courseViews);
        return courseViews;
    }

    @Override
    public List<CourseView> getCourseByMajor(Integer major, Integer studentId, String term) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 获取学生已选课程
        List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
        
        // 获取专业名称
        String majorName = majorMapper.majorNameByMajorId(major);
        
        // 过滤获取指定专业和学期的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            CourseView courseView = (CourseView) entry.getValue();
            if (courseView.getMajorName().equals(majorName) && courseView.getTerm().equals(term)) {
                courseViews.add(courseView);
            }
        });
        
        // 标记已选课程
        signSelectedCourse(chooseList, courseViews);
        return courseViews;
    }

    @Override
    public List<CourseView> getCourseByTypeAndMajor(String type, Integer major, Integer studentId, String term) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 获取学生已选课程
        List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
        
        // 获取专业名称
        String majorName = majorMapper.majorNameByMajorId(major);
        
        // 过滤获取指定类型、专业和学期的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            CourseView courseView = (CourseView) entry.getValue();
            if (courseView.getCourseType().equals(type) && 
                courseView.getMajorName().equals(majorName) && 
                courseView.getTerm().equals(term)) {
                courseViews.add(courseView);
            }
        });
        
        // 标记已选课程
        signSelectedCourse(chooseList, courseViews);
        return courseViews;
    }

    @Override
    public List<CourseView> getCourseByCourseName(String courseName, Integer studentId, String term) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 获取学生已选课程
        List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
        
        // 过滤获取名称包含搜索关键字和学期的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            CourseView courseView = (CourseView) entry.getValue();
            if (courseView.getCourseName().contains(courseName) && 
                courseView.getTerm().equals(term)) {
                courseViews.add(courseView);
            }
        });
        
        // 标记已选课程
        signSelectedCourse(chooseList, courseViews);
        return courseViews;
    }
    
    @Override
    public List<CourseView> getCourseByTeacherId(Integer teacherId) {
        // 从Redis缓存中获取课程数据
        Map<Object, Object> courseViewsMap = redisOperator.getCourseViews();
        
        // 如果缓存为空，重新加载缓存
        if (courseViewsMap.size() == 0) {
            loadListener.init();
            courseViewsMap = redisOperator.getCourseViews();
        }
        
        // 过滤获取指定教师的课程
        ArrayList<CourseView> courseViews = new ArrayList<>();
        courseViewsMap.entrySet().forEach(entry -> {
            CourseView courseView = (CourseView) entry.getValue();
            // 注意：CourseView中可能没有teacherId字段，需要在数据库查询中添加
            // 这里假设有一个教师ID字段，实际实现可能需要修改
            if (teacherId.equals(courseView.getTeacherId())) {
                courseViews.add(courseView);
            }
        });
        
        return courseViews;
    }
    
    /**
     * 标记学生已选的课程
     * @param chooseList 已选课程列表
     * @param courseViews 课程视图列表
     */
    private void signSelectedCourse(List<Choose> chooseList, ArrayList<CourseView> courseViews) {
        for (Choose choose : chooseList) {
            for (CourseView courseView : courseViews) {
                if (choose.getCourseId().equals(courseView.getCourseId())) {
                    courseView.setStatus("1");
                }
            }
        }
    }
} 