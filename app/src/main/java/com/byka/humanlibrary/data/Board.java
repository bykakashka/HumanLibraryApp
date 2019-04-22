package com.byka.humanlibrary.data;

import java.io.Serializable;

public class Board implements Serializable, ListElement {
    private Integer boardNo;
    private Long bookId;
    private Integer maxUsers;
    private String bookName;
    private Long sessionId;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getStringRepresentation() {
        return getBoardNo() + " " + getBookName() + "    " + getMaxUsers();
    }
}
