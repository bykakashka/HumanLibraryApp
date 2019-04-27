package com.byka.humanlibrary.provider;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.RestHelper;
import com.byka.humanlibrary.wrapper.GenericListWrapper;

import org.springframework.http.ResponseEntity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractListProvider<PROCESS, RESULT> extends AsyncTask<String, PROCESS, List<RESULT>> {

    private static final Logger logger = Logger.getLogger("AbstractListProvider");

    private ArrayAdapter<RESULT> arrayAdapter;

    private WeakReference<ProgressBar> progressBar;

    private ResponseHelper messageHelper;

    AbstractListProvider(final ArrayAdapter<RESULT> adapter, ProgressBar bar, Context context) {
        this.arrayAdapter = adapter;
        this.progressBar = new WeakReference<>(bar);
        this.messageHelper = new ResponseHelper(context);
    }

    AbstractListProvider(final ArrayAdapter<RESULT> adapter, ProgressBar bar, TextView textView) {
        this.arrayAdapter = adapter;
        this.progressBar = new WeakReference<>(bar);
        this.messageHelper = new ResponseHelper(textView);
    }

    @Override
    protected List<RESULT> doInBackground(String... params) {
        try {

            ResponseEntity<? extends GenericListWrapper<RESULT>> resp = new RestHelper().getResponseAsArray(params[0], getWrapperClass());

            GenericListWrapper<RESULT> result = messageHelper.parseResponse(resp);

            return result == null ? null : result.getContent();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in the rest call", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<RESULT> result) {
        if (result != null) {
            this.arrayAdapter.addAll(result);
        }

        if (this.progressBar != null) {
            this.progressBar.get().setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPreExecute() {
        this.progressBar.get().setVisibility(View.VISIBLE);
    }

    protected abstract Class<? extends GenericListWrapper<RESULT>> getWrapperClass();
}
