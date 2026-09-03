package com.jf.service.impl;

import com.jf.common.ServerResponse;
import com.jf.config.LoadListener;
import com.jf.exception.CancelCourseException;
import com.jf.exception.ChooseCourseException;
import com.jf.mapper.ChooseMapper;
import com.jf.mapper.CourseMapper;
import com.jf.pojo.Choose;
import com.jf.pojo.viewpojo.ChooseGrade;
import com.jf.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 选课服务实现类
 */
@Service
public class ChooseServiceImpl implements ChooseService {

    @Autowired
    private ChooseMapper chooseMapper;
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private LoadListener loadListener;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse<Object> chooseCourse(Integer studentId, Integer courseId, Date firstDatetime, Date lastDatetime, String term) {
        try {
            List<Choose> chooseList = chooseMapper.selectByStudentId(studentId, term);
            if (chooseList.size() >= 3) {
                return ServerResponse.createByErrorMessage("选课达到上限3门");
            }
            
            if (chooseList != null) {
                for (Choose choose : chooseList) {
                    if (choose.getCourseId().equals(courseId)) {
                        return ServerResponse.createByErrorMessage("你已选过该课了");
                    }
                }
            }
            
            int i = courseMapper.UpdateCourseToChooseCourse(courseId, lastDatetime);
            if (i == 0) {
                return ServerResponse.createByErrorMessage("课程已经被选完");
            } else if (i == 1) {
                Choose choose = new Choose();
                choose.setCourseId(courseId);
                choose.setStudentId(studentId);
                choose.setFirstDatetime(firstDatetime);
                choose.setLastDatetime(lastDatetime);
                choose.setMyTerm(term);
                chooseMapper.chooseCourse(choose);
                // 选课成功后，重新初始化课程缓存
                loadListener.init();
                return ServerResponse.createBySuccessMessage("选课成功");
            } else {
                throw new ChooseCourseException("001", "选课失败");
            }
        } catch (ChooseCourseException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ChooseCourseException("002", "选课失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse<Object> deleteCourse(Integer studentId, Integer courseId, Date firstDatetime, Date lastDatetime) {
        try {
            int k = chooseMapper.deleteByCourseIdAndStudentId(courseId, studentId);

            if (k == 1) {
                int i = courseMapper.UpdateCourseToDeleteCourse(courseId, lastDatetime);
                if (i == 0) {
                    throw new CancelCourseException("001", "退课失败,课程余量为负数");
                } else if (i == 1) {
                    // 退课成功后，重新初始化课程缓存
                    loadListener.init();
                    return ServerResponse.createBySuccessMessage("退课成功");
                } else {
                    throw new CancelCourseException("002", "课程总数更新错误");
                }
            } else {
                throw new CancelCourseException("003", "退课条数不等于1条记录");
            }
        } catch (CancelCourseException ex) {
            throw ex;
        } catch (Exception e) {
            throw new CancelCourseException("004", "退课失败: " + e.getMessage());
        }
    }

    @Override
    public List<Choose> getTeacherCourse(Integer courseId) {
        return chooseMapper.selectByCourseId(courseId);
    }
    
    @Override
    public List<ChooseGrade> getStudentsGradeList(Integer courseId) {
        return chooseMapper.getStudentsGradeList(courseId);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServerResponse<String> updateGrade(Integer chooseId, Float usualGrade, Float examGrade, Float allGrade, Date lastDatetime) {
        try {
            int result = chooseMapper.updateGradeByChooseId(chooseId, usualGrade, examGrade, allGrade, lastDatetime);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("成绩录入成功");
            } else {
                return ServerResponse.createByErrorMessage("成绩录入失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("成绩录入失败: " + e.getMessage());
        }
    }
} 