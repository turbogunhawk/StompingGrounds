package me.tedlandis.stompinggrounds;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * base implementation of {@link MvpPresenter}. Uses a {@link java.lang.ref.WeakReference} to
 * reference the attached view.
 */
public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> viewReference;

    @Override
    public void onAttach(V view) {
        viewReference = new WeakReference<V>(view);
    }

    @Override
    public void onDetach(boolean retainInstance) {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    /**
     * returns the reference to the view of this presenter. always check {@link #isAttached()} to
     * avoid NullPointerExceptions.
     *
     * @return the view, or null if no view attached
     */
    @Nullable V getView() {
        return viewReference != null ? viewReference.get() : null;
    }

    /**
     *checks if there is view attached
     * @return true if a view is attached, false otherwise
     */
    public boolean isAttached() {
        return viewReference != null && viewReference.get() != null;
    }
}