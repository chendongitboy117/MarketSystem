package com.hcyzzl.mks.backend.common;

import java.io.Serializable;

/**
 * api 封装
 * @Author chendong
 * @create 2019/4/7 18:37
 */
public class Result<T> implements Serializable {

    public static final int DUPLICATE_DATA_ERROR = 600;
    public static final int SERVER_ERROR = 500;
    public static final int SUCCESS = 200;

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
