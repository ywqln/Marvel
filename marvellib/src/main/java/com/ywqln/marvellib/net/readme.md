# Retrofit + OkHttp + RxJava

## network gradle setting
``` java

    // - 网络请求框架 retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    
    // - 网络请求 client(okHttpClient)
    implementation 'com.squareup.okhttp3:okhttp:3.12.1'
    
    // - 数据解析器 addConverterFactory(GsonConverterFactory.create())
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    
    // - 网络请求适配器 addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
    
    // AndroidSchedulers.mainThread()
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
```


``` java
/**
 * 描述：模拟api
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/19
 */
public interface MyApi {
    /**
     * 根据学号获取某个学生信息
     * @param studentNumber
     * @return
     */
    @GET("/getStudent")
    Call<ResultDo> getStudent(@Query("num") String studentNumber);

    /**
     * 获取一个班级的所有学生信息
     * @param classNumber
     * @return
     */
    @POST("/getStudents")
    Observable<List<ResultDo>> getStudents(@Query("classNum") String classNumber);
}
```

``` java
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
        Call<ResultDo> result = api.getStudent("007");
        result.enqueue(new Callback<ResultDo>() {
            @Override
            public void onResponse(Call<ResultDo> call, Response<ResultDo> response) {

            }

            @Override
            public void onFailure(Call<ResultDo> call, Throwable t) {

            }
        });

        // 使用Observeble方式请求一个api
        retrofit.create(MyApi.class)
                .getStudents("")
                .subscribe(new Observer<List<ResultDo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // 在订阅时，也就是请求数据前
                    }

                    @Override
                    public void onNext(List<ResultDo> value) {
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
```
