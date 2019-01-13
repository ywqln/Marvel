package com.ywqln.marvel.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ywqln.marvel.R;
import com.ywqln.marvel.databinding.ActivityMainBinding;
import com.ywqln.marvel.model.doImpl.HeroDo;

/**
 * 描述：应用主启动页面
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HeroDo hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        hero = new HeroDo().setName("钢铁侠").setGender("男").setAge(45).setPower("有钱");
        binding.setHero(hero);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
                    Snackbar.make(view, "替换成你自己的事件", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    hero = new HeroDo().setName("黑寡妇").setGender("女").setAge(38).setPower("漂亮");
                    binding.setHero(hero);
                }
        );
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
}
