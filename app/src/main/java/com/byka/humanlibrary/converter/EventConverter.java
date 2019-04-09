package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.Event;

import org.json.JSONException;
import org.json.JSONObject;


public class EventConverter extends AbstractConverter<Event> {

    @Override
    public Event convert(JSONObject object) throws JSONException {
        final Event event = new Event();
        event.setAddress(getNullableString(object, "address"));
        event.setId(object.getLong("id"));
        event.setCity(getNullableString(object, "city"));
        event.setInfo(getNullableString(object, "info"));
        event.setStatus(getNullableString(object, "status"));
        event.setDate(getNullableString(object, "date"));
        event.setName(getNullableString(object, "name"));

        return event;
    }
}
