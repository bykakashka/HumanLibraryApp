package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.byka.humanlibrary.adapter.viewHolder.BookViewHolder;
import com.byka.humanlibrary.data.Book;

import java.util.List;

public class BookListAdapter extends AbstractListAdapter<Book, BookViewHolder> {
    public BookListAdapter(List<Book> data, Context context) {
        super(data, android.R.layout.simple_expandable_list_item_1, context);
    }

    @Override
    protected BookViewHolder newViewHolder() {
        return new BookViewHolder();
    }

    @Override
    protected void fillViewHolder(BookViewHolder viewHolder, View view) {
        viewHolder.setName(view.findViewById(android.R.id.text1));
    }

    @Override
    protected void fillView(Book item, BookViewHolder viewHolder) {
        viewHolder.getName().setText(item.getName());
        viewHolder.getName().setTextColor(Color.BLACK);
    }
}
