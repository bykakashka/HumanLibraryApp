package com.byka.humanlibrary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.constants.RestConstants;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditText newIpText = view.findViewById(R.id.newIp);
        Button changeIpButton = view.findViewById(R.id.changeIp);
        newIpText.setText(RestConstants.SERVER_ENDPOINT);
        changeIpButton.setOnClickListener(v -> {
            RestConstants.SERVER_ENDPOINT = newIpText.getText().toString();
        });

        getActivity().setTitle("Settings");
    }
}
