package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.viewHolder.NewsViewHolder;
import com.byka.humanlibrary.data.News;

import java.util.List;

public class NewsListAdapter extends AbstractListAdapter<News, NewsViewHolder> {
    public NewsListAdapter(List<News> data, Context context) {
        super(data, R.layout.news_row_item, context);
    }

    @Override
    protected NewsViewHolder newViewHolder() {
        return new NewsViewHolder();
    }

    @Override
    protected void fillViewHolder(NewsViewHolder viewHolder, View view) {
        viewHolder.setTitle(view.findViewById(R.id.newsTitleId));
        viewHolder.setText(view.findViewById(R.id.newsTextId));
    }

    @Override
    protected void fillView(News item, NewsViewHolder viewHolder) {
        viewHolder.getTitle().setText(item.getTitle());
        viewHolder.getTitle().setTextColor(Color.BLACK);

        viewHolder.getText().setText(item.getText());
        viewHolder.getText().setTextColor(Color.BLACK);

    }
}
