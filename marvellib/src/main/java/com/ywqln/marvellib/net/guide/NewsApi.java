package com.ywqln.marvellib.net.guide;

import com.ywqln.marvellib.net.guide.dto.response.NewsResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * 描述:新闻相关接口.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public interface NewsApi {
    @GET("/toutiao/index")
    @Headers("Authorization:APPCODE 1b56403f51d84d66812ac7aa274fefe6")
    Observable<NewsResp> getNews(@Query("type") String type);
}
