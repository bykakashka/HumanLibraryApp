package com.byka.humanlibrary.provider;

import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.converter.NewsConverter;
import com.byka.humanlibrary.data.News;



public class NewsProvider extends AbstractListProvider<Void, News, News> {
    public NewsProvider(ArrayAdapter<News> adapter, ProgressBar progressBar) {
        super(adapter, progressBar);
    }

    @Override
    protected Converter<News> getConverter() {
        return new NewsConverter();
    }

    @Override
    protected News getElementRepresentation(News element) {
        return element;
    }
}
