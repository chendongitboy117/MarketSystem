package com.hcyzzl.mks.backend.common;

/**
 * json 数据返回的工厂类
 * @Author chendong
 * @create 2019/4/7 18:41
 */
public class ResultBuilder {
    private ResultBuilder() {
    }

    public static Result<Boolean> build(boolean success) {
        return new Result<>(success ? 200 : 500, "", Boolean.TRUE);
    }

    /**
     * 构建一个成功的 Result
     *
     * @param <T> 数据的类型
     * @return 成功的 Result 对象
     */
    public static <T> Result<T> success() {
        return new Result<>(200, null, null);
    }

    /**
     * 构建一个成功的 Result
     *
     * @param data 成功时返回的数据
     * @param <T>  数据的类型
     * @return 成功的 Result 对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, null, data);
    }

    /**
     * 构建一个失败的 Result
     *
     * @param <T> 数据的类型
     * @return 失败的 Result 对象
     */
    public static <T> Result<T> error() {
        return new Result<>(500, null, null);
    }

    /**
     * 构建一个失败的 Result
     *
     * @param message 失败返回的数据
     * @param <T>     数据的类型
     * @return 失败的 Result 对象
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    /**
     * 构建一个失败的 Result
     *
     * @param code    请求状态码，成功的状态码只有 200，但错误的并非如此
     * @param message 失败返回的数据
     * @param <T>     数据的类型
     * @return 失败的 Result 对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
