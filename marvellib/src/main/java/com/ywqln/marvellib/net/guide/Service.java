package com.ywqln.marvellib.net.guide;


import com.ywqln.marvellib.net.guide.dto.response.ResultResp;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：Service
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/19
 */
public class Service {
    private final String baseUrl = "";

    public void request() {

        // 简单的创建和请求方式

        // 创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        // 使用Call的方式请求一个api
        MyApi api = retrofit.create(MyApi.class);
        Call<ResultResp> result = api.getStudent("007");
        result.enqueue(new Callback<ResultResp>() {
            @Override
            public void onResponse(Call<ResultResp> call, Response<ResultResp> response) {

            }

            @Override
            public void onFailure(Call<ResultResp> call, Throwable t) {

            }
        });

        // 使用Observeble方式请求一个api
        retrofit.create(MyApi.class)
                .getStudents("")
                .subscribe(new Observer<List<ResultResp>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 在订阅时，也就是请求数据前
                    }

                    @Override
                    public void onNext(List<ResultResp> value) {
                        // 数据结果返回
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 请求发生错误
                    }

                    @Override
                    public void onComplete() {
                        // 完成本次请求
                    }
                });


    }
}
