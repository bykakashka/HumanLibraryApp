package com.byka.humanlibrary.adapter.viewHolder;

import android.widget.ImageView;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.ViewHolder;


public class BoardViewHolder implements ViewHolder {
    private TextView bookName;
    private TextView maxUsers;
    private TextView sequence;
    private ImageView icon;

    public TextView getBookName() {
        return bookName;
    }

    public void setBookName(TextView bookName) {
        this.bookName = bookName;
    }

    public TextView getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(TextView maxUsers) {
        this.maxUsers = maxUsers;
    }

    public TextView getSequence() {
        return sequence;
    }

    public void setSequence(TextView sequence) {
        this.sequence = sequence;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
}
