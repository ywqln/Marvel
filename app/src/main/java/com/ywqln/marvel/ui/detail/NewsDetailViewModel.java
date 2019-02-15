package com.ywqln.marvel.ui.detail;

import com.ywqln.marvel.net.guide.dto.response.model.News;

/**
 * 描述：详情页的ViewModel实现，内部可包含数据请求
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class NewsDetailViewModel implements IDetailViewModel {
    private News mNews;

    public NewsDetailViewModel(News news) {
        mNews = news;
    }

    @Override
    public String getTitle() {
        return mNews.getTitle();
    }

    @Override
    public String getNewsUrl() {
        return mNews.getUrl();
    }

    @Override
    public String getImgUrl() {
        return mNews.getThumbUrl();
    }


    @Override
    public String getDetail() {
        return mNews.getCategory() + "  " + mNews.getAuthor() + "  " + mNews.getDate();
    }
}
