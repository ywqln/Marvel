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

## 注解相关

### @Target
```
    @Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。

　　作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）

　　取值(ElementType)有：
　　　　1.CONSTRUCTOR:用于描述构造器
　　　　2.FIELD:用于描述域
　　　　3.LOCAL_VARIABLE:用于描述局部变量
　　　　4.METHOD:用于描述方法
　　　　5.PACKAGE:用于描述包
　　　　6.PARAMETER:用于描述参数
　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明

```
### @Retention
``` 
    　　@Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
    
    　　作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
    
    　　取值（RetentionPoicy）有：
    
    　　　　1.SOURCE:在源文件中有效（即源文件保留）
    　　　　2.CLASS:在class文件中有效（即class保留）
    　　　　3.RUNTIME:在运行时有效（即运行时保留）
    
    　　Retention meta-annotation类型有唯一的value作为成员，它的取值来自java.lang.annotation.RetentionPolicy的枚举类型值。
```
### @Documented
```
　　@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
```

## 思考
- connectTimeout 和 readTimeout 和 writeTimeout 区别
- addInterceptor 和 addNetworkInterceptor 区别

- flatMap
- 列表展示
- mvp
- RxPermission
- LitePal
- 激光推送

