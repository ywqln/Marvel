package com.ywqln.app.ui.main.news;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywqln.app.net.guide.dto.response.model.News;
import com.ywqln.app.R;
import com.ywqln.marvel.adapter.BaseViewHolder;
import com.ywqln.marvel.adapter.annotation.LayoutResId;
import com.ywqln.marvel.glide.ImageLoader;

/**
 * 描述:广告ViewHolder.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/22
 */
@LayoutResId(R.layout.item_advert)
public class AdvertViewHolder extends BaseViewHolder<News> {

    private ImageView mAdvertImg;
    private TextView mAdvertTextView;

    public AdvertViewHolder(Class clz, ViewGroup parent) {
        super(clz, parent);
    }

    @Override
    protected void dataBind(News data) {
        ImageLoader.instance().load(mAdvertImg, data.getThumbUrl());
        mAdvertTextView.setText(data.getTitle());
    }


    @Override
    public void preInit() {

    }

    @Override
    public void initView(@Nullable View view) {
        mAdvertImg = view.findViewById(R.id.img_advert);
        mAdvertTextView = view.findViewById(R.id.tv_advert);
    }

    @Override
    public void completed(@Nullable View view) {

    }
}
