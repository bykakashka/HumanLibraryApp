package com.byka.humanlibrary.provider;

import android.widget.ProgressBar;

import com.byka.humanlibrary.adapter.BoardListAdapter;
import com.byka.humanlibrary.converter.BoardConverter;
import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.data.Board;

public class BoardsProvider extends AbstractListProvider<Void, Board, Board> {

    public BoardsProvider(BoardListAdapter adapter, ProgressBar progressBar) {
        super(adapter, progressBar);
    }

    @Override
    protected Converter<Board> getConverter() {
        return new BoardConverter();
    }

    @Override
    protected Board getElementRepresentation(Board element) {
        return element;
    }
}
