package com.ywqln.marvel.ui.main;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.ui.detail.NewsDetailActivity;
import com.ywqln.marvel.ui.main.home.HomeFragment;
import com.ywqln.marvel.ui.main.news.NewsListFragment;
import com.ywqln.marvel.ui.main.personal.PersonalFragment;
import com.ywqln.marvellib.base.ui.BaseActivity;
import com.ywqln.marvellib.utils.WLog;

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
    public void preInit() {
        WLog.p("hello", "你好");
        WLog.tempP("网页Log", "临时信息");
        WLog.p("no", "我不好");
        WLog.p("Just Do It");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView(View view) {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> menuSelected(item));
        navigation.setSelectedItemId(navigation.getMenu().getItem(1).getItemId());
    }

    @Override
    public void completed(View view) {

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

        String newsJson =
                "{\"author_name\":\"灵动娱乐说\",\"category\":\"娱乐\",\"date\":\"2019-02-15 17:57\","
                        + "\"thumbnail_pic_s\":\"http://06imgmini.eastday"
                        + ".com/mobile/20190215/2019021517_c44056c0177844c89c2913fc0bd3703b_6480_mwpm_03200403.jpg\",\"title\":\"同样头戴皇冠，鞠婧祎清纯，热巴唐嫣似女王，她却是来搞笑的\",\"uniquekey\":\"1769ec7ef17bcd5ee4bc4384fa3f3ac3\",\"url\":\"http://mini.eastday.com/mobile/190215175710446.html\"}";
        switch (item.getItemId()) {
            case R.id.navigation_news_list:
                showFragment(0);
                return true;
            case R.id.navigation_home:
                showFragment(1);
                return true;
            case R.id.navigation_personal:
                showFragment(2);
                startActivity(
                        new Intent(MainActivity.this, NewsDetailActivity.class).putExtra(PARAM_JSON,
                                newsJson));
                return true;
        }
        return false;
    }
}
