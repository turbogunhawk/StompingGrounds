package me.tedlandis.stompinggrounds.ui.signin;

import android.text.TextUtils;

import me.tedlandis.stompinggrounds.ui.base.BasePresenter;

public class UserNamePasswordSignInPresenter
        extends BasePresenter<IUserNamePasswordSignInView>
        implements UserNamePasswordSignInView.ActionListener {

    private String username;
    private String password;

    @Override
    public void onUsernameTextChanged(String username) {
        this.username = username;
        setButtonEnabledIfValid();
    }

    @Override
    public void onPasswordTextChanged(String password) {
        this.password = password;
        setButtonEnabledIfValid();
    }

    @Override
    public void onLoginButtonClicked() {

    }

    private void setButtonEnabledIfValid() {
        boolean enabled = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
        getView().setLoginButtonEnabled(enabled);
    }
}