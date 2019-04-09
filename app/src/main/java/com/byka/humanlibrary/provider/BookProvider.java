package com.byka.humanlibrary.provider;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.byka.humanlibrary.converter.BookConverter;
import com.byka.humanlibrary.data.Book;
import com.byka.humanlibrary.helpers.RestHelper;

import java.lang.ref.WeakReference;

public class BookProvider extends AsyncTask<String, Void, Book> {

    private WeakReference<TextView> bookWeakReference;

    public BookProvider(TextView book) {
        this.bookWeakReference = new WeakReference<>(book);
    }

    @Override
    protected Book doInBackground(String... params) {
        try {
            return new RestHelper().getSingleResponse(params[0], new BookConverter());
        } catch (Exception e) {
            Log.d("BookProvider", "Exception in the rest call", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Book result) {
        this.bookWeakReference.get().setText(result.getLongDescription());
    }
}
