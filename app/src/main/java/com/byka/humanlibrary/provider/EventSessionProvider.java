package com.byka.humanlibrary.provider;

import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.converter.SessionConverter;
import com.byka.humanlibrary.data.Session;

public class EventSessionProvider extends AbstractListProvider<Void, Session, Session> {
    public EventSessionProvider(ArrayAdapter<Session> adapter, TextView emptySessionsView, ProgressBar progressBar) {
        super(adapter, progressBar, emptySessionsView);
    }

    @Override
    protected Converter<Session> getConverter() {
        return new SessionConverter();
    }

    @Override
    protected Session getElementRepresentation(Session element) {
        return element;
    }
}
