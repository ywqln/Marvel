package com.ywqln.marvellib.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ywqln.marvellib.widget.StatusBarNotification;

/**
 * 描述:Activity基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected StatusBarNotification.Builder mNotificationBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationBuilder = new StatusBarNotification.Builder(this);
    }
}
