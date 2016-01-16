package me.tedlandis.stompinggrounds.ui.signin.username;

import android.text.TextUtils;

import me.tedlandis.stompinggrounds.api.LoginService;
import me.tedlandis.stompinggrounds.ui.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UserNamePasswordSignInPresenter
        extends BasePresenter<IUserNamePasswordSignInView>
        implements UserNamePasswordSignInView.ActionListener {
    private LoginService loginService;

    private String username;
    private String password;

    public UserNamePasswordSignInPresenter(LoginService loginService) {
        this.loginService = loginService;
    }

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
        loginService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private void setButtonEnabledIfValid() {
        boolean enabled = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
        getView().setLoginButtonEnabled(enabled);
    }
}