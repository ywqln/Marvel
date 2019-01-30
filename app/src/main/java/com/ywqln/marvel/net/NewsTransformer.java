package com.ywqln.marvel.net;

import com.ywqln.marvellib.net.exception.ApiException;
import com.ywqln.marvellib.net.guide.dto.response.NewsResp;

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
            throw new ApiException(tNewsResp.getErrorCode(), tNewsResp.getReason());
        });
    }
}
