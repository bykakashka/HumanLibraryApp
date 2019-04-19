package com.byka.humanlibrary.fragments.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.activity.EventActivity;
import com.byka.humanlibrary.activity.MainActivity;
import com.byka.humanlibrary.adapter.EventListAdapter;
import com.byka.humanlibrary.data.Event;
import com.byka.humanlibrary.provider.LatestEventProvider;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.ArrayList;

public class EventListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView nearestEventsListView = view.findViewById(R.id.nearestEventListView);
        final EventListAdapter adapter = new EventListAdapter(new ArrayList<>(5), view.getContext());
        nearestEventsListView.setAdapter(adapter);
        nearestEventsListView.setOnItemClickListener((parent, v, pos, id) -> {
            Event event = adapter.getItem(pos);
            Intent intent = new Intent(getContext(), EventActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });
        new LatestEventProvider(adapter, view.findViewById(R.id.emptyNearestEvents), getActivity().findViewById(R.id.progressBar)).execute(getResources().getString(R.string.eventNearestUrl));

        getActivity().setTitle(getResources().getString(R.string.affiche_title));
    }
}
