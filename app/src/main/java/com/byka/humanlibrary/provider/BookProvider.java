package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.byka.humanlibrary.data.Book;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.helpers.RestHelper;

import org.springframework.http.ResponseEntity;

import java.lang.ref.WeakReference;

public class BookProvider extends AsyncTask<String, Void, Book> {

    private WeakReference<TextView> bookWeakReference;

    private ResponseHelper responseHelper;

    public BookProvider(TextView book) {
        this.bookWeakReference = new WeakReference<>(book);
        this.responseHelper = new ResponseHelper(book);
    }

    @Override
    protected Book doInBackground(String... params) {
        try {
            ResponseEntity<Book> resp = new RestHelper().getSingleResponse(params[0], Book.class);
            return responseHelper.parseResponse(resp);
        } catch (Exception e) {
            Log.d("BookProvider", "Exception in the rest call", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Book result) {
        if (result != null) {
            this.bookWeakReference.get().setText(result.getLongDescription());
        }
    }
}
