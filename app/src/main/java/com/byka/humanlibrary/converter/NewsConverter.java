package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.News;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsConverter extends AbstractConverter<News> {

    public News convert(JSONObject jsonObject) throws JSONException {
        final News news = new News();
        news.setId(jsonObject.getLong("id"));
        news.setTitle(getNullableString(jsonObject, "title"));
        news.setText(getNullableString(jsonObject,"text"));
        news.setAuthorName(jsonObject.getString("authorName"));
        news.setAuthorId(jsonObject.getLong("authorId"));
        return news;
    }
}
