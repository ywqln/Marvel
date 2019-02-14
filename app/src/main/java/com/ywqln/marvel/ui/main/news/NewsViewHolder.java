package com.ywqln.marvel.ui.main.news;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ywqln.marvel.R;
import com.ywqln.marvel.net.guide.dto.response.model.News;
import com.ywqln.marvellib.adapter.BaseViewHolder;
import com.ywqln.marvellib.adapter.annotation.LayoutResId;

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
    private AppCompatTextView mTvNewsType;
    private AppCompatTextView mTvNewsFrom;
    private AppCompatTextView mTvNewsDate;

    public NewsViewHolder(Class clz, ViewGroup parent) {
        super(clz, parent);
    }

    @Override
    protected void dataBind(News data) {
        Glide.with(mImgNews.getContext()).load(data.getThumbnail_pic_s()).into(mImgNews);
        mTvNewsTitle.setText(data.getTitle());
        mTvNewsType.setText(data.getCategory());
        mTvNewsFrom.setText(data.getAuthor_name());
        mTvNewsDate.setText(data.getDate());
    }

    @Override
    public void preInit() {

    }

    @Override
    public void initView(@Nullable View view) {
        mImgNews = view.findViewById(R.id.img_news);
        mTvNewsTitle = view.findViewById(R.id.tv_news_title);
        mTvNewsType = view.findViewById(R.id.tv_news_type);
        mTvNewsFrom = view.findViewById(R.id.tv_news_from);
        mTvNewsDate= view.findViewById(R.id.tv_news_date);
    }

    @Override
    public void completed(@Nullable View view) {

    }
}
