package com.ywqln.marvellib.base.ui;

import android.support.v4.app.Fragment;

import com.ywqln.marvellib.mvp.BaseView;
import com.ywqln.marvellib.widget.StatusBarNotification;

/**
 * 描述:Fragment基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class BaseFragment extends Fragment implements BaseView {

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
