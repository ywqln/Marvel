package com.ywqln.app.ui.main;

import com.ywqln.app.net.guide.dto.response.model.News;
import com.ywqln.app.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvel.mvp.BaseModel;
import com.ywqln.marvel.mvp.BasePresenter;
import com.ywqln.marvel.mvp.BaseView;
import com.ywqln.marvel.net.observer.ResponseObserver;

/**
 * 描述:MainActivity的契约.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/12
 */
public interface MainContract {

    interface NewsFragment {
        /**
         * P
         */
        abstract class Presenter extends
                BasePresenter<MainContract.NewsFragment.View, MainContract.NewsFragment.Model> {

            public Presenter(MainContract.NewsFragment.View view,
                    MainContract.NewsFragment.Model model) {
                super(view, model);
            }

            /**
             * 获取新闻列表
             */
            public abstract void getNewsList();

            public abstract News getAdvert();
        }

        /**
         * V
         */
        interface View extends BaseView {
            void showNewsList(NewsResult newsResult);
        }

        /**
         * M
         */
        interface Model extends BaseModel {
            /**
             * 获取用户权限
             *
             * @return 用户权限数据
             */
            void getNews(String type, ResponseObserver<NewsResult> observer);

            News getAdvert();

        }
    }
}
