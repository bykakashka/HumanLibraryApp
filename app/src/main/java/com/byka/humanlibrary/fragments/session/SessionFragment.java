package com.byka.humanlibrary.fragments.session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.BoardListAdapter;
import com.byka.humanlibrary.listener.NameClickListener;
import com.byka.humanlibrary.provider.BoardsProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SessionFragment extends Fragment {
    public static SessionFragment newInstance(Long sessionId, String title) {
        SessionFragment fragment = new SessionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sessionId", Long.toString(sessionId));
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(getArguments().getString("title"));

        ListView boardsView = view.findViewById(R.id.sessionBoards);
        BoardListAdapter adapter = new BoardListAdapter(new ArrayList<>(), view.getContext(), new NameClickListener(getActivity()));
        boardsView.setAdapter(adapter);

        new BoardsProvider(adapter, getActivity().findViewById(R.id.progressBar), getContext()).execute(getResources().getString(R.string.boardBySessionUrl) + getArguments().getString("sessionId"));
    }
}
