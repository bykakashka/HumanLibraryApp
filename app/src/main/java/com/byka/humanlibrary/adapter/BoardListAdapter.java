package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.viewHolder.BoardViewHolder;
import com.byka.humanlibrary.data.Board;
import com.byka.humanlibrary.data.RegistrationEvent;
import com.byka.humanlibrary.listener.NameClickListener;
import com.byka.humanlibrary.provider.BoardRegistrationProvider;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BoardListAdapter extends AbstractListAdapter<Board, BoardViewHolder> {
    private NameClickListener listener;

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

        viewHolder.getIcon().setImageResource(item.isUserRegistered() ? R.drawable.registered : R.drawable.user_white_icon);

        viewHolder.getMaxUsers().setOnClickListener(v -> onUsersClick(item));

        viewHolder.getIcon().setOnClickListener(v -> onUsersClick(item));

        viewHolder.getBookName().setOnClickListener(listener.getListener(item));
    }

    private void onUsersClick(Board item) {
        BoardRegistrationProvider provider = new BoardRegistrationProvider();
        provider.execute(item.getSessionId().toString(), item.getBoardNo().toString());
        try {
            RegistrationEvent result = provider.get();
            if (result != null && Boolean.TRUE.equals(result.getSuccess())) {
                Toast.makeText(getContext(), "Registered for book " + item.getBookName(), Toast.LENGTH_SHORT).show();
                item.setMaxUsers(item.getMaxUsers() - 1); // TODO refresh from response
                notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Failed" + item.getBookName(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Failed" + item.getBookName(), Toast.LENGTH_SHORT).show();
        }

    }
}
