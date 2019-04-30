package com.byka.humanlibrary.provider;

import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.data.Session;
import com.byka.humanlibrary.wrapper.GenericListWrapper;
import com.byka.humanlibrary.wrapper.SessionListWrapper;

public class EventSessionProvider extends AbstractListProvider<Void, Session> {
    public EventSessionProvider(ArrayAdapter<Session> adapter, TextView emptySessionsView, ProgressBar progressBar) {
        super(adapter, progressBar, emptySessionsView);
    }

    @Override
    protected Class<? extends GenericListWrapper<Session>> getWrapperClass() {
        return SessionListWrapper.class;
    }
}
