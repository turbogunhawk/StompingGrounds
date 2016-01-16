package me.tedlandis.stompinggrounds.ui.signin.username;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import me.tedlandis.stompinggrounds.R;
import me.tedlandis.stompinggrounds.ui.base.BaseMvpView;
import me.tedlandis.stompinggrounds.util.TextChangedAdapter;

public class UserNamePasswordSignInView
        extends BaseMvpView
        implements IUserNamePasswordSignInView {

    @Bind(R.id.user_name_edit_text)
    EditText userNameEditText;
    @Bind(R.id.password_edit_text)
    EditText passwordEditText;
    @Bind(R.id.user_name_password_login_button)
    Button loginButton;

    private ActionListener mListener;

    public UserNamePasswordSignInView(View view, ActionListener listener) {
        super(view);
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        this.mListener = listener;

        userNameEditText.addTextChangedListener(new TextChangedAdapter() {
            @Override
            public void afterTextChanged(Editable username) {
                mListener.onUsernameTextChanged(username.toString());
            }
        });
        passwordEditText.addTextChangedListener(new TextChangedAdapter() {
            @Override
            public void afterTextChanged(Editable password) {
                mListener.onPasswordTextChanged(password.toString());
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginButtonClicked();
            }
        });
    }

    @Override
    public void setLoginButtonEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void showError(String message) {

    }

    public interface ActionListener {
        void onUsernameTextChanged(String username);

        void onPasswordTextChanged(String password);

        void onLoginButtonClicked();
    }
}