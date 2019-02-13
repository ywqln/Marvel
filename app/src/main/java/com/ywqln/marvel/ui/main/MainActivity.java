package com.ywqln.marvel.ui.main;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.ywqln.marvel.R;
import com.ywqln.marvel.ui.detail.DetailActivity;
import com.ywqln.marvel.ui.main.fragment.HomeFragment;
import com.ywqln.marvel.ui.main.fragment.NewsListFragment;
import com.ywqln.marvel.ui.main.fragment.PersonalFragment;
import com.ywqln.marvellib.base.ui.BaseActivity;

/**
 * 描述：应用程序主启动页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MainActivity extends BaseActivity {

    private NewsListFragment mNewsListFragment;
    private HomeFragment mHomeFragment;
    private PersonalFragment mPersonalFragment;

    @Override
    protected void preInit() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> menuSelected(item));
        navigation.setSelectedItemId(navigation.getMenu().getItem(1).getItemId());
    }

    @Override
    protected void completed() {

    }

    private void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mNewsListFragment != null) {
            transaction.hide(mNewsListFragment);
        }
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mPersonalFragment != null) {
            transaction.hide(mPersonalFragment);
        }

        if (index == 0) {
            if (mNewsListFragment == null) {
                mNewsListFragment = new NewsListFragment();
                transaction.add(R.id.frame_placeholder, mNewsListFragment).commit();
                return;
            }
            transaction.show(mNewsListFragment).commit();
            return;
        }
        if (index == 1) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                transaction.add(R.id.frame_placeholder, mHomeFragment).commit();
                return;
            }
            transaction.show(mHomeFragment).commit();
            return;
        }
        if (index == 2) {
            if (mPersonalFragment == null) {
                mPersonalFragment = new PersonalFragment();
                transaction.add(R.id.frame_placeholder, mPersonalFragment).commit();
                return;
            }
            transaction.show(mPersonalFragment).commit();
            return;
        }
    }

    public boolean menuSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_news_list:
                showFragment(0);
                return true;
            case R.id.navigation_home:
                showFragment(1);
                return true;
            case R.id.navigation_personal:
                showFragment(2);
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
                return true;
        }
        return false;
    }
}
