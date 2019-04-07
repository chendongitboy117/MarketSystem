package com.hcyzzl.mks.backend.common;

/**
 * 前后端分离后的 Api 基类
 * @Author chendong
 * @create 2019/4/7 18:38
 */
public abstract class BaseApi extends BaseController {
    /**
     * 成功后返回的值
     * 返回码为默认值 200
     * @param data 需要返回的数据
     * @param <T> 返回数据的类型
     * @return 经过 Result 包装后的数据
     */
    protected <T> Result<T> success(T data) {
        return success(Result.SUCCESS, data);
    }

    /**
     * 成功后返回的值
     *
     * @param code 返回码
     * @param data 需要返回的数据
     * @param <T>  返回数据的类型
     * @return 经过 Result 包装后的数据
     */
    protected <T> Result<T> success(Integer code, T data) {
        return build(code, null, data);
    }

    /**
     * 失败后返回的值
     * 如果成功后需要返回消息，请使用 {@link BaseApi#success(java.lang.Object)} 方法
     * 错误返回码为默认值 500
     *
     * @param msg 错误消息
     * @return 经过 Result 包装后的数据
     */
    protected <T> Result<T> error(String msg) {
        return error(Result.SERVER_ERROR, msg);
    }

    /**
     * 失败后返回的值
     * 如果成功后需要返回消息，请使用 {@link BaseApi#success(java.lang.Object)} 方法
     *
     * @param code 返回码
     * @param msg  错误消息
     * @return 经过 Result 包装后的数据
     */
    protected <T> Result<T> error(Integer code, String msg) {
        return build(code, msg, null);
    }

    /**
     * 包装一个 Result 对象
     *
     * @param code 返回码
     * @param msg  错误消息
     * @param data 需要返回的数据
     * @param <T>  返回数据的类型
     * @return 经过 Result 包装后的数据
     */
    protected <T> Result<T> build(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    /**
     * 增删改的Result对象
     *
     * @param result
     * @return
     */
    protected Result build(Boolean result) {
        return ResultBuilder.build(result);
    }
}
