package com.ywqln.marvel.ui.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityDetailBinding;

/**
 * 描述：详情页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class DetailActivity extends AppCompatActivity implements IDetailEventHandler {

    private ActivityDetailBinding binding;
    private DetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mViewModel = new DetailViewModel();
        binding.setViewModel(mViewModel);
        binding.setEvent(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        binding.setViewModel(mViewModel);
    }
}