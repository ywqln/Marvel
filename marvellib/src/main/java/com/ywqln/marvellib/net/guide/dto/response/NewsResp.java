package com.ywqln.marvellib.net.guide.dto.response;

import com.ywqln.marvellib.net.guide.dto.response.model.NewsResult;

/**
 * 描述:新闻响应对象.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class NewsResp {
    private String reason;
    private NewsResult result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public NewsResult getResult() {
        return result;
    }

    public void setResult(NewsResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
