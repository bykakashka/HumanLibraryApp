package com.byka.humanlibrary.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface Converter<T> {
    List<T> convert(JSONArray array) throws JSONException;

    T convert(JSONObject object) throws JSONException;
}
