package com.ywqln.marvel.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 描述:基础BaseAdapter.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/13
 */
public abstract class BaseAdapter<M> extends RecyclerView.Adapter<BaseViewHolder<M>> {
    /**
     * 数据源
     */
    private List<M> dataSource;

    private OnItemClickListener<M> mOnItemClickListener;

    public List<M> getDataSource() {
        return dataSource;
    }

    public BaseAdapter setDataSource(List<M> dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 创建ViewHodler
     *
     * @param parent   父容器
     * @param viewType viewType
     * @return BaseViewHolder<M>
     */
    protected abstract BaseViewHolder<M> viewHolder(ViewGroup parent, int viewType);

    @NonNull
    @Override
    public BaseViewHolder<M> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder<M> viewHolder = viewHolder(parent, viewType);
        viewHolder.setOnItemClickListener(mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<M> baseViewHolder, int position) {
        baseViewHolder.dataBindView(getDataSource().get(position), position);
    }

    @Override
    public int getItemCount() {
        if (dataSource == null) {
            return 0;
        }
        return dataSource.size();
    }
}
