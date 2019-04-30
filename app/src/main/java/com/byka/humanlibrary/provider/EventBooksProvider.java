package com.byka.humanlibrary.provider;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.byka.humanlibrary.adapter.BookListAdapter;
import com.byka.humanlibrary.data.Book;
import com.byka.humanlibrary.wrapper.BookListWrapper;
import com.byka.humanlibrary.wrapper.GenericListWrapper;


public class EventBooksProvider extends AbstractListProvider<Void, Book> {
    public EventBooksProvider(final BookListAdapter adapter, final TextView emptyCatalogView, final ProgressBar progressBar) {
        super(adapter, progressBar, emptyCatalogView);
    }

    @Override
    protected Class<? extends GenericListWrapper<Book>> getWrapperClass() {
        return BookListWrapper.class;
    }
}
