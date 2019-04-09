package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.byka.humanlibrary.adapter.viewHolder.SessionViewHolder;
import com.byka.humanlibrary.data.Session;

import java.util.List;

public class SessionListAdapter extends AbstractListAdapter<Session, SessionViewHolder> {
    public SessionListAdapter(List<Session> data, Context context) {
        super(data, android.R.layout.simple_list_item_1, context);
    }

    @Override
    protected SessionViewHolder newViewHolder() {
        return new SessionViewHolder();
    }

    @Override
    protected void fillViewHolder(SessionViewHolder viewHolder, View view) {
        viewHolder.setName(view.findViewById(android.R.id.text1));
    }

    @Override
    protected void fillView(Session item, SessionViewHolder viewHolder) {
        viewHolder.getName().setText(item.getStringRepresentation());
        viewHolder.getName().setTextColor(Color.BLACK);
    }


}
