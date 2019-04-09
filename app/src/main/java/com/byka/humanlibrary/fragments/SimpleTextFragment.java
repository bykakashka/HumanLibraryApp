package com.byka.humanlibrary.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.byka.humanlibrary.R;
import org.jetbrains.annotations.NotNull;

public class SimpleTextFragment extends Fragment {
    private static final String TEXT_PARAM = "text";
    private static final String TITLE_PARAM = "title";

    public static SimpleTextFragment newInstance(String text) {
        SimpleTextFragment eventFragment = new SimpleTextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_PARAM, text);
        eventFragment.setArguments(bundle);
        return eventFragment;
    }

    public static SimpleTextFragment newInstance(String text, String title) {
        SimpleTextFragment eventFragment = new SimpleTextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_PARAM, text);
        bundle.putString(TITLE_PARAM, title);
        eventFragment.setArguments(bundle);
        return eventFragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_text, parent, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        TextView infoText = view.findViewById(R.id.simpleFragmentText);
        infoText.setText(getArguments().getString(TEXT_PARAM));
        infoText.setTextColor(Color.BLACK);

        String title = getArguments().getString(TITLE_PARAM);
        if (title != null) {
            getActivity().setTitle(title);
        }
    }
}
