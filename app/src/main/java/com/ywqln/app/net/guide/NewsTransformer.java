package com.ywqln.app.net.guide;

import com.ywqln.marvel.net.exception.ResponseException;
import com.ywqln.app.net.guide.dto.response.NewsResp;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * 描述:数据流转换，描述数据正常返回和异常返回.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/30
 */
public class NewsTransformer<T> implements ObservableTransformer<NewsResp<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<NewsResp<T>> upstream) {
        return upstream.map(tNewsResp -> {
            if (tNewsResp.getErrorCode() == 0) {
                return tNewsResp.getResult();
            }
            throw new ResponseException(tNewsResp.getErrorCode(),
                    tNewsResp.getReason()).setDiagnostic(tNewsResp.getReason());
        });
    }
}
