package com.byka.humanlibrary.wrapper;

import java.util.List;

public class GenericListWrapper<T> {
    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
