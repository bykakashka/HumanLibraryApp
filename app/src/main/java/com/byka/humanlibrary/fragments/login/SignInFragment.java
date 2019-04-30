package com.byka.humanlibrary.fragments.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.activity.MainActivity;
import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.data.NewUserData;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.StorageHelper;
import com.byka.humanlibrary.provider.LoginProvider;
import com.byka.humanlibrary.provider.RegistrationProvider;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import static com.byka.humanlibrary.constants.Constants.UserConstants.ANON_NICKNAME;

public class SignInFragment extends Fragment {

    private ResponseHelper responseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        responseHelper = new ResponseHelper(getContext());
        return inflater.inflate(R.layout.fragment_login, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoCompleteTextView nickname = view.findViewById(R.id.nickname);

        EditText password = view.findViewById(R.id.password);
        password.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin(nickname.getText().toString(), password.getText().toString());
                return true;
            }
            return false;
        });

        Button signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> attemptLogin(nickname.getText().toString(), password.getText().toString()));

        Button registratinoButton = view.findViewById(R.id.registration_button);
        registratinoButton.setOnClickListener(v -> attemptRegistration(nickname.getText().toString(), password.getText().toString()));

    }

    private void attemptRegistration(String nickname, String pass) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPref.edit();

        RegistrationProvider provider = new RegistrationProvider(nickname, pass);
        provider.execute(new NewUserData(nickname, pass));

        try {
            ResponseEntity<User> responseEntity = provider.get();
            User user = responseHelper.parseResponse(responseEntity);

            if (user != null && !ANON_NICKNAME.equalsIgnoreCase(user.getNickname())) {
                StorageHelper.saveUserInfo(nickname + ":" + pass, user.getNickname(), editor);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {

        }

    }

    private void attemptLogin(String login, String pass) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPref.edit();

        String token = login + ":" + pass;
        RestConstants.AUTH_TOKEN = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
        LoginProvider loginProvider = new LoginProvider(login, pass, getContext());
        loginProvider.execute();

        try {
            User user = loginProvider.get();
            if (user != null && !ANON_NICKNAME.equalsIgnoreCase(user.getNickname())) {
                StorageHelper.saveUserInfo(token, user.getNickname(), editor);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
