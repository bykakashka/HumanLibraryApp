package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.RegistrationEvent;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationEventConverter extends AbstractConverter<RegistrationEvent> {
    @Override
    public RegistrationEvent convert(JSONObject object) throws JSONException {
        RegistrationEvent registrationEvent = new RegistrationEvent();
        registrationEvent.setSuccess(object.getBoolean("success"));
        return registrationEvent;
    }
}
