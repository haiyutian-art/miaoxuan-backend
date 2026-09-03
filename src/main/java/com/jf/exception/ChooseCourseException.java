package com.jf.exception;

/**
 * 选课异常类
 * 用于处理选课过程中的业务异常
 */
public class ChooseCourseException extends RuntimeException {
    private String code;
    private String msg;

    /**
     * 构造函数
     * @param code 错误码
     * @param msg 错误信息
     */
    public ChooseCourseException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
} 