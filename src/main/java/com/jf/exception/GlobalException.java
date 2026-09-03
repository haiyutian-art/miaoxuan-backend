package com.jf.exception;

import com.jf.common.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * 统一处理系统中的各种异常，并返回适当的响应给客户端
 */
@ControllerAdvice
public class GlobalException {

    /**
     * 处理系统通用异常
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ServerResponse<Object> systemException(Exception ex) {
        System.out.println("全局异常发生Exception：");
        ex.printStackTrace();
        return ServerResponse.createByErrorMessage("系统错误,请稍后再试！");
    }

    /**
     * 处理退课相关异常
     */
    @ResponseBody
    @ExceptionHandler(value = {CancelCourseException.class})
    public ServerResponse<Object> cancelCourseException(CancelCourseException ex) {
        System.out.println("全局异常发生CancelCourseException：" + ex.getCode());
        ex.printStackTrace();
        return ServerResponse.createByErrorMessage(ex.getMsg());
    }

    /**
     * 处理选课相关异常
     */
    @ResponseBody
    @ExceptionHandler(value = {ChooseCourseException.class})
    public ServerResponse<Object> chooseCourseException(ChooseCourseException ex) {
        System.out.println("全局异常发生ChooseCourseException：" + ex.getCode());
        ex.printStackTrace();
        return ServerResponse.createByErrorMessage(ex.getMsg());
    }
} 