package com.ywqln.marvel.ui.main.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.ui.BaseFragment;

/**
 * 描述:主页Fragment.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/16
 */
public class HomeFragment extends BaseFragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void preInit() {

    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_red_light, android.R.color.holo_purple);

        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
    }

    @Override
    public void completed(View view) {

    }

    private void refresh() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
        }).start();

    }
}
