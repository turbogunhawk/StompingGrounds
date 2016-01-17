package me.tedlandis.stompinggrounds.ui.signin.username;

import me.tedlandis.stompinggrounds.api.LoginService;
import me.tedlandis.stompinggrounds.api.RxSchedulerApplier;
import me.tedlandis.stompinggrounds.ui.base.BasePresenter;
import me.tedlandis.stompinggrounds.util.TextUtils;
import rx.functions.Action1;
import rx.functions.Func1;

public class UserNamePasswordSignInPresenter
        extends BasePresenter<IUserNamePasswordSignInView>
        implements UserNamePasswordSignInView.ActionListener {
    private LoginService loginService;
    private RxSchedulerApplier mSchedulerApplier;

    private String username;
    private String password;

    public UserNamePasswordSignInPresenter(LoginService loginService) {
        this(loginService, RxSchedulerApplier.SUBSCRIBE_IO_OBSERVE_ANDROID_MAIN);
    }

    public UserNamePasswordSignInPresenter(LoginService loginService, RxSchedulerApplier schedulerApplier) {
        this.loginService = loginService;
        this.mSchedulerApplier = schedulerApplier;
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
                .filter(new Func1<Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean success) {
                        if (!success) {
                            throw new RuntimeException("i guess we failed");
                        }
                        return true;
                    }
                })
                .compose(mSchedulerApplier.<Boolean>getTransformer())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        getView().showLoading(false);
                        getView().onLoginSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showLoading(false);
                        getView().showError("uh oh");
                    }
                });
    }

    private void setButtonEnabledIfValid() {
        boolean enabled = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password);
        getView().setLoginButtonEnabled(enabled);
    }
}