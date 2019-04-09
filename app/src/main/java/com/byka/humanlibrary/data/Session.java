package com.byka.humanlibrary.data;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable, ListElement {
    private Long id;
    private Integer sequence;
    private String startDate;
    private String endDate;
    private List<Board> boards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    @Override
    public String getStringRepresentation() {
        return getSequence() + " " + getStartDate() + " - " + getEndDate();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
