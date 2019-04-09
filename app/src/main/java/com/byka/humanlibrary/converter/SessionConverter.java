package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.Session;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionConverter extends AbstractConverter<Session>{
    @Override
    public Session convert(JSONObject object) throws JSONException {
        final Session session = new Session();
        session.setStartDate(object.getString("startDate"));
        session.setEndDate(object.getString("endDate"));
        session.setSequence(object.getInt("sequence"));
        session.setId(object.getLong("id"));
        return session;
    }
}
