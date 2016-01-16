package me.tedlandis.stompinggrounds.ui.base;

/**
 * contract for presenters
 */
public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);

    void detachView();
}