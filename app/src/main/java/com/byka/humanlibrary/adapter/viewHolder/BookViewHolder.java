package com.byka.humanlibrary.adapter.viewHolder;

import android.widget.TextView;

import com.byka.humanlibrary.adapter.ViewHolder;

public class BookViewHolder implements ViewHolder {
    private TextView name;

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
