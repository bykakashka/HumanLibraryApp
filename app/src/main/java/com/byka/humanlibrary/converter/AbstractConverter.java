package com.byka.humanlibrary.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<T> implements Converter<T> {
    public List<T> convert(JSONArray array) throws JSONException {
        final List<T> list = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            final T item = this.convert(array.getJSONObject(i));
            list.add(item);
        }

        return list;
    }

    protected String getNullableString(JSONObject object, String fieldName) throws JSONException {
        if (object.isNull(fieldName)) {
            return null;
        } else {
            return object.getString(fieldName);
        }
    }
}
