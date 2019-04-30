package com.byka.humanlibrary.provider;

import android.content.Context;
import android.widget.ProgressBar;

import com.byka.humanlibrary.adapter.BoardListAdapter;
import com.byka.humanlibrary.data.Board;
import com.byka.humanlibrary.wrapper.BoardListWrapper;
import com.byka.humanlibrary.wrapper.GenericListWrapper;

public class BoardsProvider extends AbstractListProvider<Void, Board> {

    public BoardsProvider(BoardListAdapter adapter, ProgressBar progressBar, Context context) {
        super(adapter, progressBar, context);
    }

    @Override
    protected Class<? extends GenericListWrapper<Board>> getWrapperClass() {
        return BoardListWrapper.class;
    }
}
