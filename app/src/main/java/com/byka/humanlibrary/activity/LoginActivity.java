package com.byka.humanlibrary.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.constants.RestConstants;
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
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        String token = login + ":" + pass;
        editor.putString("token", Base64.encodeToString(token.getBytes(), Base64.DEFAULT)).apply();
        RestConstants.AUTH_TOKEN = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
        LoginProvider loginProvider = new LoginProvider(login, pass);
        loginProvider.execute();

        try {
            User user = loginProvider.get();
            String userNickname = "";
            if (user != null) {
                userNickname = user.getNickname();
            }

            editor.putString("nickname", userNickname).apply();
            onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
