package com.ywqln.marvel.webkit.base;

/**
 * 描述:WebAction.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/21
 */
public class WebAction {
    public WebAction() {
    }

    public WebAction(String action, String data) {
        this.action = action;
        this.data = data;
    }

    private String action;
    private String data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
