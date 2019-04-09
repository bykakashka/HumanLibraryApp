package com.byka.humanlibrary.adapter.viewHolder;

import android.widget.TextView;

import com.byka.humanlibrary.adapter.ViewHolder;

public class SessionViewHolder implements ViewHolder {
    private TextView name;
    private Long sessionId;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
