package com.ywqln.marvel.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvel.R;
import com.ywqln.marvellib.base.ui.BaseFragment;

/**
 * 描述:主页Fragment.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/16
 */
public class HomeFragment extends BaseFragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_home, null);
        mSwipeRefreshLayout = contentView.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_red_light, android.R.color.holo_purple);
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());

        return contentView;
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
