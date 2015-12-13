package me.tedlandis.stompinggrounds;

/**
 * root of mvp presenters
 */
public interface MvpPresenter<V extends MvpView> {
    /**
     * attach the view to this presenter
     * @param view the view to attach
     */
    void onAttach(V view);

    /**
     * called when the view is destroyed
     * @param retainInstance we'll find out
     */
    void onDetach(boolean retainInstance);
}
