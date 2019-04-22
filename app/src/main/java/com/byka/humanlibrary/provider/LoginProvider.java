package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.util.Base64;

import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.converter.LoginConverter;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.helpers.RestHelper;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginProvider extends AsyncTask<Void, Void, User> {
    private static final Logger logger = Logger.getLogger("LoginProvider");

    private String login;
    private String pass;

    public LoginProvider(String login, String pass) {
        this.pass = pass;
        this.login = login;
    }

    @Override
    protected User doInBackground(Void ... params) {
        try {
            String token = login + ":" + pass;
            RestConstants.AUTH_TOKEN = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
            return new RestHelper().getSingleResponse("/user", new LoginConverter());
        } catch (Exception e) {
            logger.log(Level.WARNING, "cannot auth", e);
            return null;
        }
    }
}
