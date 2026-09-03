package com.jf.service.teacher.impl;

import com.jf.mapper.ChooseMapper;
import com.jf.mapper.CourseMapper;
import com.jf.mapper.StudentMapper;
import com.jf.pojo.Choose;
import com.jf.pojo.Student;
import com.jf.pojo.viewpojo.ChooseGrade;
import com.jf.pojo.viewpojo.CourseView;
import com.jf.service.teacher.TeacherCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师课程服务实现类
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private ChooseMapper chooseMapper;
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public List<CourseView> getCourse(Integer teacherId) {
        return courseMapper.getCourseByTeacherId(teacherId);
    }
    
    @Override
    public List<ChooseGrade> selectByCourseId(Integer courseId) {
        return chooseMapper.getStudentsGradeList(courseId);
    }
    
    @Override
    public ChooseGrade getChooseByChooseId(Integer chooseId) {
        Choose choose = chooseMapper.selectByPrimaryKey(chooseId);
        if (choose == null) {
            return null;
        }
        
        ChooseGrade chooseGrade = new ChooseGrade();
        Student student = studentMapper.selectByPrimaryKey(choose.getStudentId());
        BeanUtils.copyProperties(choose, chooseGrade);
        chooseGrade.setStudentName(student.getStudentName());
        chooseGrade.setStudentNo(student.getStudentNo());
        
        return chooseGrade;
    }
} 