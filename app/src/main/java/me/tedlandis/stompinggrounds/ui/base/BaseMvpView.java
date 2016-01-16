package me.tedlandis.stompinggrounds.ui.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * essentially a view holder similar to recycler view's view holder
 */
public class BaseMvpView {
    private View view;

    public BaseMvpView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
    }
}