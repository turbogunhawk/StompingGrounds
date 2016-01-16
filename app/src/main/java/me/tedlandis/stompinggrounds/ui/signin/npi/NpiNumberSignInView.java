package me.tedlandis.stompinggrounds.ui.signin.npi;

import android.view.View;

import me.tedlandis.stompinggrounds.ui.base.BaseMvpView;

public class NpiNumberSignInView
        extends BaseMvpView
        implements INpiNumberSignInView {

    private ActionListener mListener;

    public NpiNumberSignInView(View view, ActionListener listener) {
        super(view);
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mListener = listener;
    }

    public interface ActionListener {
    }
}