package com.byka.humanlibrary.provider;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.EventListAdapter;
import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.converter.EventConverter;
import com.byka.humanlibrary.data.Event;


public class LatestEventProvider extends AbstractListProvider<Void, Event, Event> {
    public LatestEventProvider(final EventListAdapter adapter, TextView emptySessionsView, ProgressBar progressBar) {
        super(adapter, progressBar, emptySessionsView);
    }

    @Override
    protected Converter<Event> getConverter() {
        return new EventConverter();
    }

    @Override
    protected Event getElementRepresentation(Event element) {
        return element;
    }
}
