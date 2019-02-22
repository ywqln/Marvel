package com.ywqln.marvel.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityDetailBinding;
import com.ywqln.marvel.net.guide.dto.response.model.News;
import com.ywqln.marvel.net.test.TestAnnotation;
import com.ywqln.marvel.webview.AppWebChromeClient;
import com.ywqln.marvel.webview.AppWebViewClient;
import com.ywqln.marvellib.base.ui.BaseActivity;
import com.ywqln.marvellib.glide.ImageLoader;
import com.ywqln.marvellib.utils.CheckVirtualUtil;
import com.ywqln.marvellib.utils.WLog;
import com.ywqln.marvellib.webkit.MarvelWebView;
import com.ywqln.marvellib.widget.ProgressView;

/**
 * 描述：详情页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class NewsDetailActivity extends BaseActivity implements IDetailEventHandler {

    private ActivityDetailBinding mBinding;
    private IDetailViewModel mViewModel;
    private AppCompatImageView mImgNews;
    private FloatingActionButton mFaButton;
    private MarvelWebView<AppWebChromeClient, AppWebViewClient> mWebviewNews;
    private ProgressView mProgressView;

    @Override
    public void preInit() {
        String newsJson = getIntent().getStringExtra(PARAM_JSON);
        mViewModel = new NewsDetailViewModel(new Gson().fromJson(newsJson, News.class));
    }

    @Override
    protected void setContentView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
    }

    @Override
    public void initView(View view) {

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        mImgNews = findViewById(R.id.img_news);
        mFaButton = findViewById(R.id.fab);
        mWebviewNews = findViewById(R.id.webview_News);
        mProgressView = findViewById(R.id.pv_webview);
        mWebviewNews.webViewClient(new AppWebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                toolbarLayout.setTitle(getTitle());
            }
        }).webChromeClient(new AppWebChromeClient(mProgressView));

        debounceClick(toolbar).subscribe(o -> {
            WLog.p("点击一次");
            mNotificationBuilder.setMessage("点击一次").show();
        });
    }

    @Override
    public void completed(View view) {
        mBinding.setViewModel(mViewModel);
        mBinding.setEvent(this);
        ImageLoader.instance().load(mImgNews, mViewModel.getImgUrl());
        mWebviewNews.loadUrl(mViewModel.getNewsUrl());
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
        Snackbar.make(view, "收藏成功", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        mFaButton.setImageResource(android.R.drawable.btn_star_big_on);

        new TestAnnotation().testAnnotation();

        if (CheckVirtualUtil.isRunInVirtual()) {
            mNotificationBuilder.setMessage("非法操作！").show();
        }
    }
}
