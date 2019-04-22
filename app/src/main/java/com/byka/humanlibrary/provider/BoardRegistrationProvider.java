package com.byka.humanlibrary.provider;

import android.os.AsyncTask;

import com.byka.humanlibrary.converter.RegistrationEventConverter;
import com.byka.humanlibrary.data.RegistrationEvent;
import com.byka.humanlibrary.helpers.RestHelper;


public class BoardRegistrationProvider extends AsyncTask<String, Void, RegistrationEvent> {
    @Override
    protected RegistrationEvent doInBackground(String... params) {
        try {
            return new RestHelper().getSingleResponse("/registration/board/" + params[0] + "/" + params[1], new RegistrationEventConverter());
        } catch (Exception e) {
            return null;
        }
    }
}
