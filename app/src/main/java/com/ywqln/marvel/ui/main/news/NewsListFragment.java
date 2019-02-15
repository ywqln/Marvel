package com.ywqln.marvel.ui.main.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.net.guide.dto.response.model.News;
import com.ywqln.marvel.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvel.ui.main.MainContract;
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
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRvContentList;

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

        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
    }

    @Override
    public void completed(View view) {
        mNewsPresenter.getNewsList();
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

        NewsListAdapter<News> adapter = new NewsListAdapter<>();
        adapter.setDataSource(newsResult.getData());
        mRvContentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContentList.setAdapter(adapter);
        adapter.setOnItemClickListener((viewHolder, itemView, data, position) -> {
            String msg = "第" + position + "行数据：" + data.getTitle();
            getNotificationBuilder().tipStyle().setMessage(msg).show();
        });
    }


    private void refresh() {
        mNewsPresenter.getNewsList();
    }
}
