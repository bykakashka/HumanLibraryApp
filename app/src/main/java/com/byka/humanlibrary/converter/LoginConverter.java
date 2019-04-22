package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginConverter extends AbstractConverter<User> {
    @Override
    public User convert(JSONObject object) throws JSONException {
        User user = new User();
        user.setNickname(object.getString("nickname"));
        user.setAge(object.getInt("age"));
        user.setGender(object.getString("gender"));
        user.setName(object.getString("name"));


        JSONArray roles = object.getJSONArray("roles");
        List<String> stringRoles = new ArrayList<>();
        for (int i=0; i<roles.length(); i++) {
            stringRoles.add((String) roles.get(i));
        }
        user.setRoles(stringRoles);
        return user;
    }
}
