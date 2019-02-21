package com.ywqln.marvel.net.test;

import com.ywqln.marvellib.net.annotation.BaseUrl;
import com.ywqln.marvellib.net.annotation.Interceptors;
import com.ywqln.marvel.net.guide.dto.response.ResultResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述：模拟api
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/19
 */
@BaseUrl("http://yyy.www.qqq")
@Interceptors(ResultResp.class)
public interface MyApi {
    /**
     * 根据学号获取某个学生信息
     */
    @GET("/getStudent")
    Call<ResultResp> getStudent(@Query("num") String studentNumber);

    /**
     * 获取一个班级的所有学生信息
     */
    @POST("/getStudents")
    Observable<List<ResultResp>> getStudents(@Query("classNum") String classNumber);
}
