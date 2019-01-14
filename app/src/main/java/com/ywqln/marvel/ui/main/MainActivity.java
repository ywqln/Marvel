package com.ywqln.marvel.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityMainBinding;

/**
 * 描述：应用主启动页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MainActivity extends AppCompatActivity implements IMainEventHandler {

    private ActivityMainBinding binding;
    private MainViewModel hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        hero = new MainViewModel().setName("钢铁侠").setGender("男").setAge(45).setPower("有钱");
        binding.setViewModel(hero);
        binding.setEvent(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        hero = new MainViewModel().setName("黑寡妇").setGender("女").setAge(38).setPower("漂亮");
        binding.setViewModel(hero);
    }
}
