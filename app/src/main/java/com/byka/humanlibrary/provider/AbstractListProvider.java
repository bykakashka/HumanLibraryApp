package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.helpers.RestHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractListProvider<PROCESS, RESULT, LIST_ELEMENT> extends AsyncTask<String, PROCESS, List<RESULT>> {

    private static final Logger logger = Logger.getLogger("AbstractListProvider");

    private ArrayAdapter<LIST_ELEMENT> arrayAdapter;

    private WeakReference<ProgressBar> progressBar;

    private WeakReference<TextView> emptyListTextView;

    public AbstractListProvider(final ArrayAdapter<LIST_ELEMENT> adapter, ProgressBar bar) {
        this.arrayAdapter = adapter;
        this.progressBar = new WeakReference<>(bar);
    }

    public AbstractListProvider(final ArrayAdapter<LIST_ELEMENT> adapter, ProgressBar bar, TextView textView) {
        this.arrayAdapter = adapter;
        this.progressBar = new WeakReference<>(bar);
        this.emptyListTextView = new WeakReference<>(textView);
    }

    public AbstractListProvider(final ArrayAdapter<LIST_ELEMENT> adapter) {
        this.arrayAdapter = adapter;
    }

    @Override
    protected List<RESULT> doInBackground(String... params) {
        try {
            return new RestHelper().getResponseAsArray(params[0], getConverter());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in the rest call", e);
            return Collections.emptyList();
        }
    }

    @Override
    protected void onPostExecute(List<RESULT> result) {
        List<LIST_ELEMENT> elements = new ArrayList<>(result.size());
        for (RESULT element: result) {
            elements.add(getElementRepresentation(element));
        }
        if (elements.isEmpty() && emptyListTextView != null) {
            emptyListTextView.get().setVisibility(View.VISIBLE);
        } else {
            this.arrayAdapter.addAll(elements);
        }
        if (this.progressBar != null) {
            this.progressBar.get().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPreExecute() {
        this.progressBar.get().setVisibility(View.VISIBLE);
    }

    protected abstract Converter<RESULT> getConverter();
    protected abstract LIST_ELEMENT getElementRepresentation(RESULT element);
}
