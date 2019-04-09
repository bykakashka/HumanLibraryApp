package com.byka.humanlibrary.fragments.session;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.SessionListAdapter;
import com.byka.humanlibrary.data.Session;
import com.byka.humanlibrary.provider.EventSessionProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SessionListFragment extends Fragment {
    public static SessionListFragment newInstance(Long eventId) {
        SessionListFragment fragment = new SessionListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("eventId", Long.toString(eventId));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session_list, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.eventSessions_title));

        ListView listView = view.findViewById(R.id.sessionList);
        SessionListAdapter adapter = new SessionListAdapter(new ArrayList<>(), view.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, v, position, id) -> {
            final Session session = adapter.getItem(position);

            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            ft.replace(R.id.content_placeholder, SessionFragment.newInstance(session.getId(), session.getStringRepresentation()));
            ft.commit();
        });
        new EventSessionProvider(adapter, view.findViewById(R.id.emptySessions), getActivity().findViewById(R.id.progressBar)).execute(getResources().getString(R.string.eventSessionUrl) + getArguments().getString("eventId"));
    }
}
