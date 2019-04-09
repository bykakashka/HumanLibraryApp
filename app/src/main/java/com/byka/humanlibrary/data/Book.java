package com.byka.humanlibrary.data;

import java.io.Serializable;

public class Book implements Serializable, ListElement {
    private Long id;
    private String name;
    private String description;
    private String longDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getStringRepresentation() {
        return this.getName();
    }
}
