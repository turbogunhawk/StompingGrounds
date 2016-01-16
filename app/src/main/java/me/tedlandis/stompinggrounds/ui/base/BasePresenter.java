package me.tedlandis.stompinggrounds.ui.base;

/**
 * WRITE SOMETHING HERE!!!
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        if (!isViewAttached()) {
            throw new RuntimeException("MvpView not attached");
        }
        return view;
    }
}