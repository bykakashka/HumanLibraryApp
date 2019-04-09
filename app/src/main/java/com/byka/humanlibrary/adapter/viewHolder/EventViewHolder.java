package com.byka.humanlibrary.adapter.viewHolder;

import android.widget.TextView;

import com.byka.humanlibrary.adapter.ViewHolder;

public class EventViewHolder implements ViewHolder {
    private TextView name;
    private TextView date;

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }
}
