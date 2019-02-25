package com.ywqln.marvel.ui;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * 描述:创建视图的周期.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public interface ICreateView {
    /**
     * 准备实例化
     */
    void preInit();

    /**
     * 查找控件
     */
    void initView(@Nullable View view);

    /**
     * 完成准备工作
     */
    void completed(@Nullable View view);
}
