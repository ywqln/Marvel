package com.ywqln.marvellib.adapter;

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

    public List<M> getDataSource() {
        return dataSource;
    }

    public BaseAdapter setDataSource(List<M> dataSource) {
        this.dataSource = dataSource;
        return this;
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
        return viewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<M> mBaseViewHolder, int position) {
        mBaseViewHolder.dataBind(getDataSource().get(position));
    }

    @Override
    public int getItemCount() {
        if (dataSource == null) {
            return 0;
        }
        return dataSource.size();
    }
}
