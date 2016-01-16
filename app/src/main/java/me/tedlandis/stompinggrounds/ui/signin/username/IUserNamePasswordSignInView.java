package me.tedlandis.stompinggrounds.ui.signin.username;

import me.tedlandis.stompinggrounds.ui.base.MvpView;

public interface IUserNamePasswordSignInView extends MvpView {
    void setLoginButtonEnabled(boolean enabled);

    void showLoading(boolean show);

    void onLoginSuccess();

    void showError(String message);
}