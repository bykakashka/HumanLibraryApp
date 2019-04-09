package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.byka.humanlibrary.converter.InfoConverter;
import com.byka.humanlibrary.data.Info;
import com.byka.humanlibrary.data.InfoWrapper;
import com.byka.humanlibrary.helpers.RestHelper;

import java.lang.ref.WeakReference;

public class InfoPageProvider extends AsyncTask<String, Void, Info> {

    private WeakReference<TextView> textViewWeakReference;
    private WeakReference<InfoWrapper> infoWeakReference;

    public InfoPageProvider(TextView textView, InfoWrapper infoWrapper) {
        this.textViewWeakReference = new WeakReference<>(textView);
        this.infoWeakReference = new WeakReference<>(infoWrapper);
    }

    @Override
    protected Info doInBackground(String... params) {
        try {
            return new RestHelper().getSingleResponse(params[0], new InfoConverter());
        } catch (Exception e) {
            Log.d("InfoPageProvider", "Exception in the rest call", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Info result) {
        if (result == null) {
            textViewWeakReference.get().setText("empty"); // TODO
        } else {
            textViewWeakReference.get().setText(result.getText());
            this.infoWeakReference.get().setInfo(result);
        }
    }
}
