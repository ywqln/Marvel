package com.ywqln.marvellib.adapter;

import android.view.View;

/**
 * 描述:BaseAdapter的ItemView点击事件监听.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/15
 */
public interface OnItemClickListener<M> {
    void onItemClick(BaseViewHolder<M> viewHolder, View itemView, M data, int position);
}
