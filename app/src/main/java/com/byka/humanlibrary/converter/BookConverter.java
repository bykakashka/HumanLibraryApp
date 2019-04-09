package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.Book;

import org.json.JSONException;
import org.json.JSONObject;

public class BookConverter extends AbstractConverter<Book> {
    @Override
    public Book convert(JSONObject object) throws JSONException {
        final Book book = new Book();
        book.setId(object.getLong("id"));
        book.setName(object.getString("name"));
        book.setDescription(object.getString("description"));
        book.setLongDescription(object.getString("longDescription"));
        return book;
    }
}
