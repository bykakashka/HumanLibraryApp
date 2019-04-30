package com.byka.humanlibrary.fragments.session;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.BoardListAdapter;
import com.byka.humanlibrary.data.RegistrationEvent;
import com.byka.humanlibrary.helpers.ResponseHelper;
import com.byka.humanlibrary.listener.NameClickListener;
import com.byka.humanlibrary.provider.BoardRegistrationProvider;
import com.byka.humanlibrary.provider.BoardsProvider;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

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

    private ResponseHelper responseHelper;

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
        responseHelper = new ResponseHelper(getContext());
        BoardListAdapter adapter = new BoardListAdapter(new ArrayList<>(), view.getContext(), new NameClickListener(getActivity()));

        adapter.setListener(item -> {
            BoardRegistrationProvider provider = new BoardRegistrationProvider();
            if (!item.isCurrentRegistered()) {
                provider.execute(buildRegistrateUrl(item.getSessionId(), item.getBoardNo()));
            } else {
                provider.execute(buildUnregistrateUrl(item.getSessionId()));
            }

            String messageToShow = "";

            try {
                ResponseEntity<RegistrationEvent> result = provider.get();

                RegistrationEvent registrationEvent = responseHelper.parseResponse(result);

                if (registrationEvent != null) {
                    messageToShow = registrationEvent.getMessage();
                } else {
                    messageToShow = "Failed" + item.getBookName();
                }
            } catch (Exception e) {
                messageToShow = "Failed" + item.getBookName();
            }
            Toast.makeText(getContext(), messageToShow, Toast.LENGTH_SHORT).show();
            onViewCreated(view, savedInstanceState);
        });
        boardsView.setAdapter(adapter);

        new BoardsProvider(adapter, getActivity().findViewById(R.id.progressBar), getContext()).execute(getResources().getString(R.string.boardBySessionUrl) + getArguments().getString("sessionId"));
    }

    private String buildRegistrateUrl(@NotNull Long sessionId, @NotNull Integer boardNo) {
        return "/session/registrate/" +sessionId.toString() + "/" + boardNo.toString();
    }

    private String buildUnregistrateUrl(@NotNull Long sessionId) {
        return "/session/unregistrate/" +sessionId.toString();
    }
}
