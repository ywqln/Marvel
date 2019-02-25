package com.ywqln.marvel.ui.main.news;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvel.R;
import com.ywqln.marvel.net.guide.dto.response.model.News;
import com.ywqln.marvel.adapter.BaseViewHolder;
import com.ywqln.marvel.adapter.annotation.LayoutResId;
import com.ywqln.marvel.glide.ImageLoader;

/**
 * 描述:新闻ViewHolder.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
@LayoutResId(R.layout.item_news)
public class NewsViewHolder extends BaseViewHolder<News> {

    private AppCompatImageView mImgNews;
    private AppCompatTextView mTvNewsTitle;
    private AppCompatTextView mTvNewsCategory;
    private AppCompatTextView mTvNewsAuthor;
    private AppCompatTextView mTvNewsDate;
    private ConstraintLayout mBox;

    public NewsViewHolder(Class clz, ViewGroup parent) {
        super(clz, parent);
    }

    @Override
    protected void dataBind(News data) {
        ImageLoader.instance().load(mImgNews, data.getThumbUrl());
        mTvNewsTitle.setText(data.getTitle());
        mTvNewsCategory.setText(data.getCategory());
        mTvNewsAuthor.setText(data.getAuthor());
        mTvNewsDate.setText(data.getDate());
    }

    @Override
    public void preInit() {

    }

    @Override
    public void initView(@Nullable View view) {
        mImgNews = view.findViewById(R.id.img_news);
        mTvNewsTitle = view.findViewById(R.id.tv_news_title);
        mTvNewsCategory = view.findViewById(R.id.tv_news_category);
        mTvNewsAuthor = view.findViewById(R.id.tv_news_author);
        mTvNewsDate = view.findViewById(R.id.tv_news_date);
        mBox = view.findViewById(R.id.box);
    }

    @Override
    public void completed(@Nullable View view) {
//        mBox.setOnClickListener(view1 -> {
//
//        });
    }
}
