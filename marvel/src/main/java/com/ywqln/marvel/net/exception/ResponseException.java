package com.ywqln.marvel.net.exception;

/**
 * 描述:api请求异常对象.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/30
 */
public final class ResponseException extends RuntimeException {
    public final int code;
    public final String message;

    public ResponseException(int code) {
        this(code, null);
    }

    public ResponseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
