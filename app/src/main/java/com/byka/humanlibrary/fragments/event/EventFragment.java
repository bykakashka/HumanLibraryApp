package com.byka.humanlibrary.fragments.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.data.Event;
import com.byka.humanlibrary.fragments.SimpleTextDefaultBarFragment;
import com.byka.humanlibrary.fragments.books.CatalogFragment;
import com.byka.humanlibrary.fragments.session.SessionListFragment;

import org.jetbrains.annotations.NotNull;


public class EventFragment extends Fragment {
    private static final String TAG = "Event fragment";
    private static final String EVENT_PARAM = "event";

    private Event event;

    public static EventFragment newInstance(Event event) {
        EventFragment eventFragment = new EventFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EVENT_PARAM, event);
        eventFragment.setArguments(bundle);
        return eventFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (Event) getArguments().getSerializable(EVENT_PARAM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(event.getTitle());

        view.findViewById(R.id.eventInfoButton).setOnClickListener(v -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            String info = event.getInfo() == null ? getResources().getString(R.string.emptyInfo) : event.getInfo();
            ft.replace(R.id.content_placeholder, SimpleTextDefaultBarFragment.newInstance(info, getResources().getString(R.string.eventInfo_title)));
            ft.commit();
        });

        view.findViewById(R.id.eventCatalogButton).setOnClickListener(v -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            ft.replace(R.id.content_placeholder, CatalogFragment.newInstance(event.getId()));
            ft.commit();
        });

        view.findViewById(R.id.eventScheduleButton).setOnClickListener(v -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            ft.replace(R.id.content_placeholder, SessionListFragment.newInstance(event.getId()));
            ft.commit();
        });
    }
}
