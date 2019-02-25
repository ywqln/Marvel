package com.ywqln.marvel.mvp;

/**
 * 描述:MVP-Presenter.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    /**
     * View
     */
    protected final V mView;

    /**
     * Model
     */
    protected final M mModel;

    public BasePresenter(V view, M model) {
        mView = view;
        mModel = model;
    }
}
