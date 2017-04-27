package com.wangzh.vultr.model.entity;

/**
 * Created by WangZH on 2017/4/27.
 */

public class HttpErrorVo {

    /**
     * http 异常的 code，如果不是HttpException异常，则为0
     */
    private int code;
    /**
     * 异常信息，如果是HttpException则为message,否则为exception.message
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
