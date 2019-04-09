package com.byka.humanlibrary.fragments.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.NewsListAdapter;
import com.byka.humanlibrary.provider.NewsProvider;
import com.byka.humanlibrary.util.DateUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class NewsFragment extends Fragment {
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_news, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.news_title));
        NewsListAdapter adapter = new NewsListAdapter(new ArrayList<>(), getContext());
        ListView newsList = view.findViewById(R.id.newsList);
        newsList.setAdapter(adapter);

        new NewsProvider(adapter, getActivity().findViewById(R.id.progressBar)).execute(getResources().getString(R.string.newsBeforeUrl) + DateUtil.convert(new Date()));
    }
}
