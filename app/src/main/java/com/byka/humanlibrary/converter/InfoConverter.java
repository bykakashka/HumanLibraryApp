package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.Info;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoConverter extends AbstractConverter<Info> {
    @Override
    public Info convert(JSONObject object) throws JSONException {
        Info info = new Info();
        info.setText(object.getString("text"));
        info.setType(object.getString("type"));
        return info;
    }
}
