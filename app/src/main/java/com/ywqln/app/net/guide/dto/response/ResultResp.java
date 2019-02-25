package com.ywqln.app.net.guide.dto.response;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 描述：返回实体
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/19
 */
public class ResultResp implements Interceptor {
    private String name = "Lina";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
