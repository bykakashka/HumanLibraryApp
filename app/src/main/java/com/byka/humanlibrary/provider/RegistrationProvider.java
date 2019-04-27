package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import com.byka.humanlibrary.data.NewUserData;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationProvider extends AsyncTask<NewUserData, Void, ResponseEntity<User>> {
    private static final Logger logger = Logger.getLogger("LoginProvider");

    private String login;
    private String pass;

    public RegistrationProvider(String login, String pass) {
        this.pass = pass;
        this.login = login;
    }

    @Override
    protected ResponseEntity<User> doInBackground(NewUserData ... params) {
        try {
            return new RestHelper().post("/signin", params[0], User.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, "cannot auth", e);
            return null;
        }
    }
}
