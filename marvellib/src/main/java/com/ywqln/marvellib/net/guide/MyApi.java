package com.ywqln.marvellib.net.guide;

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
