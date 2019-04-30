package com.byka.humanlibrary.provider;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.EventListAdapter;
import com.byka.humanlibrary.data.Event;
import com.byka.humanlibrary.wrapper.EventListWrapper;
import com.byka.humanlibrary.wrapper.GenericListWrapper;


public class LatestEventProvider extends AbstractListProvider<Void, Event> {
    public LatestEventProvider(final EventListAdapter adapter, TextView emptySessionsView, ProgressBar progressBar) {
        super(adapter, progressBar, emptySessionsView);
    }

    @Override
    protected Class<? extends GenericListWrapper<Event>> getWrapperClass() {
        return EventListWrapper.class;
    }
}
