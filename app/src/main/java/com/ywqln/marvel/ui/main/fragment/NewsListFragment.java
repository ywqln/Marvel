package com.ywqln.marvel.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvel.R;
import com.ywqln.marvel.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvel.ui.main.MainContract;
import com.ywqln.marvel.ui.main.NewsModel;
import com.ywqln.marvel.ui.main.NewsPresenter;
import com.ywqln.marvellib.base.ui.BaseFragment;

/**
 * 描述：新闻列表
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/2/1
 */
public class NewsListFragment extends BaseFragment implements MainContract.NewsFragment.View {

    private NewsPresenter mNewsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mNewsPresenter = new NewsPresenter(this,new NewsModel());
        return inflater.inflate(R.layout.fragment_newslist, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsPresenter.getNewsList();
    }

    @Override
    public void showNewsList(NewsResult newsResult) {
        getNotificationBuilder().tipStyle().setMessage(newsResult.getData().get(0).getTitle()).show();
    }
}
