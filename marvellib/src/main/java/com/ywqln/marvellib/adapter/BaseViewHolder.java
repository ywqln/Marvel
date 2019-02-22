package com.ywqln.marvellib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvellib.adapter.annotation.LayoutUtil;
import com.ywqln.marvellib.ui.ICreateView;

/**
 * 描述:ViewHolder的基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder implements ICreateView {

    private M data;
    private int position;

    public BaseViewHolder(Class clz, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(LayoutUtil.layoutResId(clz),
                parent, false));
    }

    private BaseViewHolder(@NonNull View view) {
        super(view);
        preInit();
        initView(view);
        itemView.setOnClickListener(view1 -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(this, view1, data, position);
            }
        });
        completed(view);
    }

    private OnItemClickListener<M> mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 绑定
     */
    protected void dataBindView(M data, int position) {
        this.data = data;
        this.position = position;
        dataBind(data);
    }

    /**
     * 数据绑定
     *
     * @param data 数据
     */
    protected abstract void dataBind(M data);
}
