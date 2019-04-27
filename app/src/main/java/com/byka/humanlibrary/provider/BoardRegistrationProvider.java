package com.byka.humanlibrary.provider;

import android.content.Context;
import android.os.AsyncTask;

import com.byka.humanlibrary.data.RegistrationEvent;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;


public class BoardRegistrationProvider extends AsyncTask<String, Void, ResponseEntity<RegistrationEvent>> {
    private ResponseHelper responseHelper;

    public BoardRegistrationProvider(Context context) {
        this.responseHelper = new ResponseHelper(context);
    }

    @Override
    protected ResponseEntity<RegistrationEvent> doInBackground(String... params) {
        try {
            return new RestHelper().getSingleResponse("/registration/board/" + params[0] + "/" + params[1], RegistrationEvent.class);
        } catch (Exception e) {
            return null;
        }
    }
}
