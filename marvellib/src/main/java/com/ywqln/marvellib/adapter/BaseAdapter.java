package com.ywqln.marvellib.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 描述:基础BaseAdapter.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/13
 */
public abstract class BaseAdapter<M> extends RecyclerView.Adapter {
    private List<M> dataSource;

    public List<M> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<M> dataSource) {
        this.dataSource = dataSource;
    }
}
