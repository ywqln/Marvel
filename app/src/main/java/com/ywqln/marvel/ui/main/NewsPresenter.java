package com.ywqln.marvel.ui.main;

import com.ywqln.marvel.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvellib.net.exception.ResponseException;
import com.ywqln.marvellib.net.observer.SimpleObserver;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/12
 */
public class NewsPresenter extends MainContract.NewsFragment.Presenter {
    public NewsPresenter(MainContract.NewsFragment.View view,
            MainContract.NewsFragment.Model model) {
        super(view, model);
    }

    @Override
    public void getNewsList() {
        mModel.getNews("yule", new SimpleObserver<NewsResult>() {
            @Override
            protected void onSuccess(NewsResult result) {
                mView.getNewsList(result);
            }

            @Override
            protected void onFail(ResponseException responseException) {
                mView.showError(responseException.message);
            }
        });
    }

}
