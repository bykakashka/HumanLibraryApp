package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.viewHolder.BoardViewHolder;
import com.byka.humanlibrary.data.Board;
import com.byka.humanlibrary.listener.BoardRegistrationListener;
import com.byka.humanlibrary.listener.NameClickListener;

import java.util.List;

public class BoardListAdapter extends AbstractListAdapter<Board, BoardViewHolder> {
    private NameClickListener listener;
    private BoardRegistrationListener boardRegistrationListener;

    public BoardListAdapter(List<Board> data, Context context, NameClickListener listener) {
        super(data, R.layout.board_row_item, context);
        this.listener = listener;
    }

    @Override
    protected BoardViewHolder newViewHolder() {
        return new BoardViewHolder();
    }

    @Override
    protected void fillViewHolder(BoardViewHolder viewHolder, View view) {
        viewHolder.setBookName(view.findViewById(R.id.boardBookName));
        viewHolder.setSequence(view.findViewById(R.id.boardSequence));
        viewHolder.setMaxUsers(view.findViewById(R.id.boardMaxUsers));
        viewHolder.setIcon(view.findViewById(R.id.userWhiteIcon));
    }

    @Override
    protected void fillView(Board item, BoardViewHolder viewHolder) {
        viewHolder.getBookName().setText(item.getBookName());
        viewHolder.getBookName().setTextColor(Color.BLACK);

        viewHolder.getMaxUsers().setText(item.getMaxUsers().toString());
        viewHolder.getMaxUsers().setTextColor(Color.BLACK);

        viewHolder.getSequence().setText(item.getBoardNo().toString());
        viewHolder.getSequence().setTextColor(Color.BLACK);

        viewHolder.getIcon().setImageResource(item.isCurrentRegistered() ? R.drawable.registered : R.drawable.user_white_icon);

        viewHolder.getMaxUsers().setOnClickListener(v -> boardRegistrationListener.onRegistrationEvent(item));

        viewHolder.getIcon().setOnClickListener(v -> boardRegistrationListener.onRegistrationEvent(item));

        viewHolder.getBookName().setOnClickListener(listener.getListener(item));
    }

    public void setListener(BoardRegistrationListener listener) {
        this.boardRegistrationListener = listener;
    }
}
