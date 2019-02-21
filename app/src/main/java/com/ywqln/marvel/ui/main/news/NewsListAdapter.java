package com.ywqln.marvel.ui.main.news;

import android.view.ViewGroup;

import com.ywqln.marvel.net.guide.dto.response.model.News;
import com.ywqln.marvellib.adapter.BaseAdapter;
import com.ywqln.marvellib.adapter.BaseViewHolder;

/**
 * 描述:消息列表控件的适配器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/13
 */
public class NewsListAdapter<M> extends BaseAdapter<News> {

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected BaseViewHolder<News> viewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(NewsViewHolder.class, parent);
    }

}
