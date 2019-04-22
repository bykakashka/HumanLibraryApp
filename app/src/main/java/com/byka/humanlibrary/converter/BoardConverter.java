package com.byka.humanlibrary.converter;

import com.byka.humanlibrary.data.Board;

import org.json.JSONException;
import org.json.JSONObject;

public class BoardConverter extends AbstractConverter<Board> {
    @Override
    public Board convert(JSONObject object) throws JSONException {
        final Board board = new Board();
        board.setBoardNo(object.getInt("boardNo"));
        board.setBookId(object.getLong("bookId"));
        board.setBookName(object.getString("bookName"));
        board.setMaxUsers(object.getInt("maxUsers"));
        board.setSessionId(object.getLong("sessionId"));
        return board;
    }
}
