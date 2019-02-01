package com.ywqln.marvel.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvel.R;
import com.ywqln.marvellib.base.ui.BaseFragment;

/**
 * 描述：新闻列表
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/2/1
 */
public class NewsListFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_newslist, null);
    }
}
