package com.ywqln.marvel.net.guide.dto.response.model;

/**
 * 描述:新闻实体.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class News {
    private String uniquekey;
    // 新闻标题
    private String title;
    // 新闻日期
    private String date;
    // 分类
    private String category;
    // 新闻来源
    private String author_name;
    // 新闻网页地址
    private String url;
    // 新闻缩略图
    private String thumbnail_pic_s;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
