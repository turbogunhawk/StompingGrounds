package me.tedlandis.stompinggrounds.ui.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.tedlandis.stompinggrounds.R;

public class SignInActivity extends AppCompatActivity {

    private UserNamePasswordSignInPresenter mUserNamePasswordSignInPresenter;
    private NpiNumberSignInPresenter mNpiNumberSignInPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUserNamePasswordSignInPresenter = new UserNamePasswordSignInPresenter();
        mNpiNumberSignInPresenter = new NpiNumberSignInPresenter();

        View passwordView = findViewById(R.id.username_password_view);
        IUserNamePasswordSignInView userNamePasswordSignInView =
                new UserNamePasswordSignInView(passwordView, mUserNamePasswordSignInPresenter);

        View npiView = findViewById(R.id.npi_view);
        INpiNumberSignInView npiNumberSignInView =
                new NpiNumberSignInView(npiView, mNpiNumberSignInPresenter);

        mUserNamePasswordSignInPresenter.attachView(userNamePasswordSignInView);
        mNpiNumberSignInPresenter.attachView(npiNumberSignInView);
    }
}