package me.tedlandis.stompinggrounds.ui.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.tedlandis.stompinggrounds.R;
import me.tedlandis.stompinggrounds.api.ApiProvider;
import me.tedlandis.stompinggrounds.api.LoginService;
import me.tedlandis.stompinggrounds.ui.signin.npi.INpiNumberSignInView;
import me.tedlandis.stompinggrounds.ui.signin.npi.NpiNumberSignInPresenter;
import me.tedlandis.stompinggrounds.ui.signin.npi.NpiNumberSignInView;
import me.tedlandis.stompinggrounds.ui.signin.username.IUserNamePasswordSignInView;
import me.tedlandis.stompinggrounds.ui.signin.username.UserNamePasswordSignInPresenter;
import me.tedlandis.stompinggrounds.ui.signin.username.UserNamePasswordSignInView;

public class SignInActivity extends AppCompatActivity {

    private UserNamePasswordSignInPresenter mUserNamePasswordSignInPresenter;
    private NpiNumberSignInPresenter mNpiNumberSignInPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LoginService loginService = ApiProvider.getInstance().getService(LoginService.class);
        mUserNamePasswordSignInPresenter = new UserNamePasswordSignInPresenter(loginService);
        View npiView = findViewById(R.id.npi_view);
        INpiNumberSignInView npiNumberSignInView =
                new NpiNumberSignInView(npiView, mNpiNumberSignInPresenter);

        mNpiNumberSignInPresenter = new NpiNumberSignInPresenter();
        View passwordView = findViewById(R.id.username_password_view);
        IUserNamePasswordSignInView userNamePasswordSignInView =
                new UserNamePasswordSignInView(passwordView, mUserNamePasswordSignInPresenter);

        mUserNamePasswordSignInPresenter.attachView(userNamePasswordSignInView);
        mNpiNumberSignInPresenter.attachView(npiNumberSignInView);
    }
}