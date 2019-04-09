package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.viewHolder.EventViewHolder;
import com.byka.humanlibrary.data.Event;

import java.util.List;

public class EventListAdapter extends AbstractListAdapter<Event, EventViewHolder> {
    public EventListAdapter(List<Event> data, Context context) {
        super(data, R.layout.event_row_item, context);
    }

    @Override
    protected EventViewHolder newViewHolder() {
        return new EventViewHolder();
    }

    @Override
    protected void fillViewHolder(EventViewHolder viewHolder, View view) {
        viewHolder.setName(view.findViewById(R.id.eventName));
        viewHolder.setDate(view.findViewById(R.id.eventDate));
    }

    @Override
    protected void fillView(Event item, EventViewHolder viewHolder) {
        viewHolder.getDate().setText(item.getDate());
        viewHolder.getDate().setTextColor(Color.BLACK);
        viewHolder.getName().setText(item.getName());
        viewHolder.getName().setTextColor(Color.BLACK);
    }

}
