package com.ywqln.app.ui.main.news;


import com.ywqln.app.net.guide.NewsApi;
import com.ywqln.app.net.guide.NewsTransformer;
import com.ywqln.app.net.guide.dto.response.model.News;
import com.ywqln.app.net.guide.dto.response.model.NewsResult;
import com.ywqln.app.ui.main.MainContract;
import com.ywqln.marvel.net.Requestor;
import com.ywqln.marvel.net.observer.ResponseObserver;
import com.ywqln.marvel.net.tranformer.ApiThreadTransformer;

/**
 * 描述:消息数据.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/12
 */
public class NewsModel implements MainContract.NewsFragment.Model {
    @Override
    public void getNews(String type, ResponseObserver<NewsResult> observer) {
        Requestor.instance().getApi(NewsApi.class)
                .getNews(type)
                .compose(new ApiThreadTransformer<>())
                .compose(new NewsTransformer<>())
                .subscribe(observer);
    }

    @Override
    public News getAdvert() {
        News news = new News();
        news.setTitle("北京的枫叶，什么时候去都合适，我有酒，也有故事，你有车吗？--- 快上XX二手车\n"
                + "交警：听说有人要酒驾...");
        news.setThumbUrl(
                "http://f.hiphotos.baidu"
                        + ".com/image/pic/item/d50735fae6cd7b89130246b1012442a7d9330e91.jpg");
        news.setUrl("https://github.com/ywqln/Marvel");
        news.setUniquekey("advert");

        return news;
    }
}
