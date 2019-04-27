package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.byka.humanlibrary.data.Info;
import com.byka.humanlibrary.data.InfoWrapper;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;

import java.lang.ref.WeakReference;

public class InfoPageProvider extends AsyncTask<String, Void, Info> {

    private WeakReference<TextView> textViewWeakReference;
    private WeakReference<InfoWrapper> infoWeakReference;
    private ResponseHelper responseHelper;

    public InfoPageProvider(TextView textView, InfoWrapper infoWrapper) {
        this.textViewWeakReference = new WeakReference<>(textView);
        this.infoWeakReference = new WeakReference<>(infoWrapper);
        this.responseHelper = new ResponseHelper(textView);
    }

    @Override
    protected Info doInBackground(String... params) {
        try {
            ResponseEntity<Info> resp =  new RestHelper().getSingleResponse(params[0], Info.class);
            return responseHelper.parseResponse(resp);
        } catch (Exception e) {
            Log.d("InfoPageProvider", "Exception in the rest call", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Info result) {
        if (result != null) {
            textViewWeakReference.get().setText(result.getText());
            this.infoWeakReference.get().setInfo(result);
        }
    }
}
