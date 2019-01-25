package com.ywqln.marvel.ui.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityDetailBinding;
import com.ywqln.marvellib.base.ui.BaseActivity;
import com.ywqln.marvellib.net.RequestManager;
import com.ywqln.marvellib.net.guide.NewsApi;
import com.ywqln.marvellib.net.guide.TestAnnotation;
import com.ywqln.marvellib.net.guide.dto.response.NewsResp;
import com.ywqln.marvellib.net.guide.dto.response.model.News;
import com.ywqln.marvellib.utils.WLog;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    }

    private void getNews() {
        RequestManager.instance().getApi(NewsApi.class)
                .getNews("yule")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsResp newsResp) {
                        List<News> newsList = newsResp.getResult().getData();
                        News news = newsList.get(0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
