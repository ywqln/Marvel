package com.ywqln.marvellib.net.guide;


import com.ywqln.marvellib.net.guide.dto.response.NewsResp;
import com.ywqln.marvellib.net.guide.dto.response.model.News;
import com.ywqln.marvellib.net.interceptor.LoggerInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述:请求管理器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class RequestManager {
    public final static long DEFAULT_CONNECT_TIMEOUT = 15;
    public final static long DEFAULT_READ_TIMEOUT = 15;
    public final static long DEFAULT_WRITE_TIMEOUT = 15;

//    新闻头条
//    调用地址：http://toutiao-ali.juheapi.com/toutiao/index
//    请求方式：GET
//    返回类型：JSON

//    Header：Authorization:APPCODE 你自己的AppCode
//    参数type：top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),
// caijing(财经),shishang(时尚)


    public void getNews(){
        getRetrofitBuilder()
                .build()
                .create(NewsApi.class)
                .getNews("yule")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsResp value) {
                        List<News> newsList = value.getResult().getData();
                        News news = newsList.get(0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Retrofit.Builder getRetrofitBuilder() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("http://toutiao-ali.juheapi.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient().build());
        return retrofitBuilder;
    }

    private OkHttpClient.Builder getOkHttpClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggerInterceptor())
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        return okHttpBuilder;
    }
}
