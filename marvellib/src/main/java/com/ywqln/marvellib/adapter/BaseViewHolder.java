package com.ywqln.marvellib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvellib.adapter.annotation.LayoutUtil;
import com.ywqln.marvellib.base.ICreateView;

/**
 * 描述:ViewHolder的基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder implements ICreateView {

    public BaseViewHolder(Class clz, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(LayoutUtil.layoutResId(clz),
                parent, false));
    }

    private BaseViewHolder(@NonNull View view) {
        super(view);
        preInit();
        initView(view);
        completed(view);
    }

    /**
     * 数据绑定
     *
     * @param data 数据
     */
    protected abstract void dataBind(M data);
}
