package com.ywqln.marvel.ui.main.news;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ywqln.marvellib.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:消息列表控件的适配器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/13
 */
public class NewsListAdapter<M> extends BaseAdapter<M> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        List<Integer> intList = new ArrayList<>();
        List<String> strList = new ArrayList<>();

        NewsListAdapter<String> adapter = new NewsListAdapter<>();
//        adapter.setDataSource(intList); /* 编译会失败 */
        adapter.setDataSource(strList);
        return 0;
    }
}
