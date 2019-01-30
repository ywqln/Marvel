package com.ywqln.marvellib.net.observer;


import com.ywqln.marvellib.net.exception.ResponseException;
import com.ywqln.marvellib.utils.StringUtil;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * 描述:数据返回统一处理观察者.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/29
 */
public abstract class ResponseObserver<T> implements Observer<T> {

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            int errorCode = ((HttpException) e).code();
            onFail(new ResponseException(errorCode, getMsgForCode(errorCode)));
        } else if (e instanceof ResponseException) {
            onFail((ResponseException) e);
        } else {
            onFail(new ResponseException(-1, "请求失败"));
        }
        onComplete();
    }

    protected String getMsgForCode(int code) {
        String msg = StringUtil.Empty;
        switch (code) {
            case 404:
                msg = "未找到指定地址";
                break;
            case 500:
            case 502:
                msg = "服务器内部出错";
                break;
            case 504:
                msg = "网络异常";
                break;
            default:
                msg = "请求失败";
                break;
        }
        return msg;
    }


    protected abstract void onSuccess(T result);

    protected abstract void onFail(ResponseException responseException);
}
