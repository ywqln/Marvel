package com.ywqln.marvellib.base.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ywqln.marvellib.R;
import com.ywqln.marvellib.mvp.BaseView;
import com.ywqln.marvellib.widget.StatusBarNotification;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 描述:Activity基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * 页面标题
     */
    public static final String TITLE_ACTIVITY = "TITLE_ACTIVITY";

    protected StatusBarNotification.Builder mNotificationBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationBuilder = new StatusBarNotification.Builder(this);
        preInit();
        setContentView();
        initView();
        completed();
    }

    /**
     * 准备实例化
     */
    protected abstract void preInit();

    /**
     * 设置ContentView的布局
     */
    protected abstract void setContentView();

    /**
     * 查找控件
     */
    protected abstract void initView();

    /**
     * 完成准备工作
     */
    protected abstract void completed();

    /**
     * 防止多次点击／防抖<p>
     * 阀值为1秒，1秒内只能点击一次
     */
    protected Observable<Object> debounceClick(View view) {
        // TODO: 2017/7/7 防止多次点击，下面阀值为1秒
        return RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS);

    }

    /**
     * 检查所需要的权限，并且进行授权操作
     */
    protected void checkPermission(String... permissions) {
        new RxPermissions(this).requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            return;
                        }
                        mNotificationBuilder.setMessage(
                                "权限：" + permission.name + "被拒绝,需要手动开启").show();
                        if (!permission.shouldShowRequestPermissionRationale) {
                            // 授权对话框，提示用户手动开启该权限
                            openPermission();
                            return;
                        }
                    }
                });
    }

    private void openPermission() {
        new AlertDialog.Builder(this)
                .setTitle("提示：")
                .setMessage("点击确定跳转只权限页面开启相关权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null).show();
    }


    /**
     * 设置通用的toolbar，设置标题、默认添加返回按钮,setUniversalToolbar只需调用一次
     *
     * @param id 标题
     */
    protected void setUniversalToolbar(@StringRes int id) {
        setUniversalToolbar(getString(id));
    }

    /**
     * 设置通用的toolbar，设置标题、默认添加返回按钮,setUniversalToolbar只需调用一次
     *
     * @param title 标题
     */
    protected void setUniversalToolbar(CharSequence title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String activityTitle = getIntent().getStringExtra(TITLE_ACTIVITY);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(
                    TextUtils.isEmpty(activityTitle) ? title : activityTitle);
        }
    }

    /**
     * 设置ToolbarTitle
     *
     * @param title CharSequence
     */
    protected void setToolbarTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void showError(String errorMsg) {
        mNotificationBuilder.errorStyle().setMessage(errorMsg).show();
    }
}
