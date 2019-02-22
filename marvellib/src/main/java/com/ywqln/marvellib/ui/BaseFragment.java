package com.ywqln.marvellib.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvellib.constant.ExtraConstant;
import com.ywqln.marvellib.mvp.BaseView;
import com.ywqln.marvellib.widget.StatusBarNotification;

/**
 * 描述:Fragment基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public abstract class BaseFragment extends Fragment implements BaseView, ExtraConstant,
        ICreateView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        preInit();
        View view = inflater.inflate(layoutResId(), null);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        completed(view);
    }

    /**
     * 设置ContentView的布局资源Id
     *
     * @return 布局资源Id
     */
    protected abstract int layoutResId();

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected StatusBarNotification.Builder getNotificationBuilder() {
        return getBaseActivity().mNotificationBuilder;
    }

    @Override
    public void showError(String errorMsg) {
        getBaseActivity().mNotificationBuilder.errorStyle().setMessage(errorMsg).show();
    }
}
