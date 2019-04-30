package com.byka.humanlibrary.fragments.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.activity.MainActivity;
import com.byka.humanlibrary.helpers.StorageHelper;

import org.jetbrains.annotations.NotNull;

public class UserInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logout, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            StorageHelper.removeUserInfo(editor);
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
