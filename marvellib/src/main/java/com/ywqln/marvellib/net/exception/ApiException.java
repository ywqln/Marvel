package com.ywqln.marvellib.net.exception;

/**
 * 描述:api请求异常对象.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/30
 */
public final class ApiException extends RuntimeException {
    public final int code;
    public final String message;

    public ApiException(int code) {
        this(code, null);
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
