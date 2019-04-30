package com.byka.humanlibrary.provider;

import android.os.AsyncTask;

import com.byka.humanlibrary.data.RegistrationEvent;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;


public class BoardRegistrationProvider extends AsyncTask<String, Void, ResponseEntity<RegistrationEvent>> {
    @Override
    protected ResponseEntity<RegistrationEvent> doInBackground(String... params) {
        try {
            return new RestHelper().getSingleResponse(params[0], RegistrationEvent.class);
        } catch (Exception e) {
            return null;
        }
    }
}
