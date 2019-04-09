package com.byka.humanlibrary.adapter.viewHolder;

import android.widget.ImageView;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.ViewHolder;

public class NewsViewHolder implements ViewHolder {
    private TextView title;
    private TextView text;
    private ImageView image;

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
