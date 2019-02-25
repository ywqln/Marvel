package com.ywqln.app.ui.main.news;

import android.view.ViewGroup;

import com.ywqln.app.net.guide.dto.response.model.News;
import com.ywqln.marvel.adapter.BaseAdapter;
import com.ywqln.marvel.adapter.BaseViewHolder;

/**
 * 描述:消息列表控件的适配器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/13
 */
public class NewsListAdapter<M> extends BaseAdapter<News> {

    static final int NEWS_TYPE = 1;
    static final int ADVERT_TYPE = 2;
    /**
     * 广告位
     */
    public static final int ADVERT_INDEX = 12;

    @Override
    public int getItemViewType(int position) {
        if (position == ADVERT_INDEX) {
            return ADVERT_TYPE;
        }
        return NEWS_TYPE;
    }

    @Override
    protected BaseViewHolder<News> viewHolder(ViewGroup parent, int viewType) {
        if (viewType == NEWS_TYPE) {
            return new NewsViewHolder(NewsViewHolder.class, parent);
        }
        return new AdvertViewHolder(AdvertViewHolder.class, parent);
    }

}
