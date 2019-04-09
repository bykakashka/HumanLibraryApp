package com.byka.humanlibrary.provider;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.BookListAdapter;
import com.byka.humanlibrary.converter.BookConverter;
import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.data.Book;


public class EventBooksProvider extends AbstractListProvider<Void, Book, Book> {
    public EventBooksProvider(final BookListAdapter adapter, final TextView emptyCatalogView, final ProgressBar progressBar) {
        super(adapter, progressBar, emptyCatalogView);
    }

    @Override
    protected Converter<Book> getConverter() {
        return new BookConverter();
    }

    @Override
    protected Book getElementRepresentation(Book element) {
        return element;
    }
}
