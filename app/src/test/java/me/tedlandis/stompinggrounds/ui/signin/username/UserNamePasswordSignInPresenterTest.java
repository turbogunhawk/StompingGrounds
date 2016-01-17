package me.tedlandis.stompinggrounds.ui.signin.username;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.BooleanSupplier;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import me.tedlandis.stompinggrounds.api.LoginService;
import me.tedlandis.stompinggrounds.util.TestingSchedulers;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserNamePasswordSignInPresenterTest {

    @Mock
    LoginService loginService;
    @Mock
    IUserNamePasswordSignInView view;
    private UserNamePasswordSignInPresenter presenter;

    @Before
    public void setup() {
        presenter = new UserNamePasswordSignInPresenter(loginService, TestingSchedulers.immediate());
        presenter.attachView(view);
    }

    @After
    public void tearDown() {
        presenter.detachView();
    }

    @Test
    public void login_button_disabled_both_empty() {
        presenter.onUsernameTextChanged("");
        presenter.onPasswordTextChanged("");
        verify(view, times(2)).setLoginButtonEnabled(false);
    }

    @Test
    public void login_button_disabled_username_empty() {
        presenter.onUsernameTextChanged("");
        presenter.onPasswordTextChanged("not_empty");
        verify(view, times(2)).setLoginButtonEnabled(false);
    }

    @Test
    public void login_button_disabled_password_empty() {
        presenter.onUsernameTextChanged("not_empty");
        presenter.onPasswordTextChanged("");
        verify(view, times(2)).setLoginButtonEnabled(false);
    }

    @Test
    public void login_button_enabled() {
        presenter.onUsernameTextChanged("not_empty");
        presenter.onPasswordTextChanged("not_empty");
        verify(view).setLoginButtonEnabled(eq(false));
        verify(view).setLoginButtonEnabled(eq(true));
    }

    @Test
    public void login_failed() {
        doReturn(Observable.just(Boolean.FALSE))
                .when(loginService)
                .login(anyString(), anyString());
        presenter.onLoginButtonClicked();

        verify(view).showLoading(false);
        verify(view).showError("uh oh");
    }

    @Test
    public void login_successful() {
        doReturn(Observable.just(Boolean.TRUE))
                .when(loginService)
                .login(anyString(), anyString());
        presenter.onLoginButtonClicked();

        verify(view).showLoading(false);
        verify(view).onLoginSuccess();
    }
}