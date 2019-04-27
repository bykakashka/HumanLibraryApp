package com.byka.humanlibrary.provider;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.byka.humanlibrary.data.News;
import com.byka.humanlibrary.wrapper.GenericListWrapper;
import com.byka.humanlibrary.wrapper.NewsListWrapper;


public class NewsProvider extends AbstractListProvider<Void, News> {
    public NewsProvider(ArrayAdapter<News> adapter, ProgressBar progressBar, Context context) {
        super(adapter, progressBar, context);
    }

    @Override
    protected Class<? extends GenericListWrapper<News>> getWrapperClass() {
        return NewsListWrapper.class;
    }
}
