package com.ywqln.marvel.ui.detail;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityDetailBinding;
import com.ywqln.marvel.net.guide.NewsApi;
import com.ywqln.marvel.net.guide.NewsTransformer;
import com.ywqln.marvel.net.guide.dto.response.model.NewsResult;
import com.ywqln.marvel.net.test.TestAnnotation;
import com.ywqln.marvellib.base.ui.BaseActivity;
import com.ywqln.marvellib.net.Requester;
import com.ywqln.marvellib.net.exception.ResponseException;
import com.ywqln.marvellib.net.observer.ResponseObserver;
import com.ywqln.marvellib.net.observer.SimpleObserver;
import com.ywqln.marvellib.net.tranformer.ApiThreadTransformer;
import com.ywqln.marvellib.utils.CheckVirtualUtil;
import com.ywqln.marvellib.utils.WLog;

import io.reactivex.disposables.Disposable;

/**
 * 描述：详情页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class DetailActivity extends BaseActivity implements IDetailEventHandler {

    private ActivityDetailBinding mBinding;
    private DetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mViewModel = new DetailViewModel();
        mBinding.setViewModel(mViewModel);
        mBinding.setEvent(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        debounceClick(toolbar).subscribe(o -> {
            WLog.p("点击一次");
            mNotificationBuilder.setMessage("点击一次").show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fabClick(View view) {
        Snackbar.make(view, "替换成你自己的事件", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        mViewModel.updateHero();
        mBinding.setViewModel(mViewModel);

        new TestAnnotation().testAnnotation();
//        getNews();
        getNews4Simple();

        if (CheckVirtualUtil.isRunInVirtual()) {
            mNotificationBuilder.setMessage("非法操作！").show();
        }
    }

    private void getNews() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage("等待...").create();

        Requester.instance().getApi(NewsApi.class)
                .getNews("yule")
                .compose(new ApiThreadTransformer<>())
                .compose(new NewsTransformer<>())
                .subscribe(new ResponseObserver<NewsResult>() {
                    @Override
                    protected void onSuccess(NewsResult result) {
                        int stop = 0;
                        mNotificationBuilder.setMessage("有数据").show();
                    }

                    @Override
                    protected void onFail(ResponseException responseException) {
                        mNotificationBuilder.setMessage(responseException.message).show();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        alertDialog.show();
                    }

                    @Override
                    public void onComplete() {
                        alertDialog.dismiss();
                    }
                });
    }

    private void getNews4Simple() {
        Requester.instance().getApi(NewsApi.class)
                .getNews("top")
                .compose(new ApiThreadTransformer<>())
                .compose(new NewsTransformer<>())
                .subscribe(new SimpleObserver<NewsResult>() {
                    @Override
                    protected void onSuccess(NewsResult result) {
                        int stop = 0;
                        mNotificationBuilder.setMessage("有数据").show();
                    }

                    @Override
                    protected void onFail(ResponseException responseException) {
                        mNotificationBuilder.setMessage(
                                responseException.message).show();
                    }
                });
    }

}
