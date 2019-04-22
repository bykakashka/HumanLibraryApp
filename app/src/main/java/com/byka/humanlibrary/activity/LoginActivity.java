package com.byka.humanlibrary.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.provider.LoginProvider;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AutoCompleteTextView mEmailView = findViewById(R.id.email);

        EditText mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(view -> attemptLogin(mEmailView.getText().toString(), mPasswordView.getText().toString()));
    }

    private void attemptLogin(String login, String pass) {
        try {
            LoginProvider loginProvider = new LoginProvider(login, pass);
            loginProvider.execute();
            User user = loginProvider.get();
            if (user != null) {
                TextView userNickname = MainActivity.navigationView.findViewById(R.id.nav_header_username);
                userNickname.setText(user.getNickname());
                onBackPressed();
            }
        } catch (Exception e) {

        }
    }
}
