package com.ywqln.marvel.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.ywqln.marvel.R;
import com.ywqln.marvel.ui.detail.DetailActivity;
import com.ywqln.marvel.ui.main.fragment.HomeFragment;
import com.ywqln.marvel.ui.main.fragment.PersonalFragment;
import com.ywqln.marvellib.base.ui.BaseActivity;

/**
 * 描述：应用程序主启动页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MainActivity extends BaseActivity implements IMainEventHandler {

    private HomeFragment mHomeFragment;
    private PersonalFragment mPersonalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout
// .activity_main);
//        mBinding.setEvent(this);

        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> menuSelected(item));

        showFragment(0);
    }

    private void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mPersonalFragment != null) {
            transaction.hide(mPersonalFragment);
        }

        if (index == 0) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                transaction.add(R.id.frame_placeholder, mHomeFragment).commit();
                return;
            }
            transaction.show(mHomeFragment).commit();
            return;
        }
        if (index == 1) {
            if (mPersonalFragment == null) {
                mPersonalFragment = new PersonalFragment();
                transaction.add(R.id.frame_placeholder, mPersonalFragment).commit();
                return;
            }
            transaction.show(mPersonalFragment).commit();
            return;
        }
    }

    @Override
    public boolean menuSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(0);
                mNotificationBuilder.setMessage("您选择了首页").show();
                return true;
            case R.id.navigation_dashboard:
                showFragment(1);
                mNotificationBuilder.setMessage("您选择了未知页面").show();
                return true;
            case R.id.navigation_notifications:
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
                return true;
        }
        return false;
    }
}
