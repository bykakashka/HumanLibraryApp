package com.byka.humanlibrary.provider;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginProvider extends AsyncTask<String, Void, User> {
    private static final Logger logger = Logger.getLogger("LoginProvider");

    private String login;
    private String pass;
    private ResponseHelper responseHelper;

    public LoginProvider(String login, String pass, Context context) {
        this.pass = pass;
        this.login = login;
        responseHelper = new ResponseHelper(context);
    }

    @Override
    protected User doInBackground(String ... params) {
        try {
            String token = login + ":" + pass;
            RestConstants.AUTH_TOKEN = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
            ResponseEntity<User> resp = new RestHelper().getSingleResponse("/user", User.class);
            return responseHelper.parseResponse(resp);
        } catch (Exception e) {
            logger.log(Level.WARNING, "cannot auth", e);
            return null;
        }
    }
}
