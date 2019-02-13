package com.ywqln.marvel.ui.main.news;


import com.ywqln.marvel.net.guide.NewsApi;
import com.ywqln.marvel.net.guide.NewsTransformer;
import com.ywqln.marvel.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvel.ui.main.MainContract;
import com.ywqln.marvellib.net.Requester;
import com.ywqln.marvellib.net.observer.ResponseObserver;
import com.ywqln.marvellib.net.tranformer.ApiThreadTransformer;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/12
 */
public class NewsModel implements MainContract.NewsFragment.Model {
    @Override
    public void getNews(String type, ResponseObserver<NewsResult> observer) {
        Requester.instance().getApi(NewsApi.class)
                .getNews(type)
                .compose(new ApiThreadTransformer<>())
                .compose(new NewsTransformer<>())
                .subscribe(observer);
    }
}
