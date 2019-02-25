package com.ywqln.app.ui.main.news;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.ywqln.app.ui.detail.NewsDetailActivity;
import com.ywqln.app.R;
import com.ywqln.app.net.guide.dto.response.model.News;
import com.ywqln.app.net.guide.dto.response.model.NewsResult;
import com.ywqln.app.ui.main.MainContract;
import com.ywqln.marvel.ui.BaseFragment;

import java.util.List;

/**
 * 描述：新闻列表
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/2/1
 */
public class NewsListFragment extends BaseFragment implements MainContract.NewsFragment.View {
    private NewsPresenter mNewsPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRvContentList;
    private NewsListAdapter<News> mAdapter;

    @Override
    public void preInit() {
        mNewsPresenter = new NewsPresenter(this, new NewsModel());
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_newslist;
    }

    @Override
    public void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mRvContentList = view.findViewById(R.id.rv_content_list);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_red_light, android.R.color.holo_purple);
    }

    @Override
    public void completed(View view) {
        mNewsPresenter.getNewsList();
        mAdapter = new NewsListAdapter<>();
        mAdapter.setOnItemClickListener(
                (viewHolder, itemView, data, position) -> adapter_itemClick(data));

        mSwipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout_refresh());
        mRvContentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContentList.setAdapter(mAdapter);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    /**
     * 在网络请求失败或者返回数据不在预料中的时候
     *
     * @param errorMsg 错误消息
     */
    @Override
    public void showError(String errorMsg) {
        super.showError(errorMsg);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 在数据正常返回的情况下
     *
     * @param newsResult 消息
     */
    @Override
    public void showNewsList(NewsResult newsResult) {
        mSwipeRefreshLayout.setRefreshing(false);
        getNotificationBuilder().tipStyle().setMessage(
                newsResult.getData().get(0).getTitle()).show();

        List<News> newsList = newsResult.getData();
        newsList.add(NewsListAdapter.ADVERT_INDEX, mNewsPresenter.getAdvert());
        mAdapter.setDataSource(newsList);
        mAdapter.notifyDataSetChanged();
    }

    private void adapter_itemClick(News data) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(PARAM_JSON, new Gson().toJson(data));
        startActivity(intent);
    }

    private void swipeRefreshLayout_refresh() {
        mNewsPresenter.getNewsList();
    }
}
