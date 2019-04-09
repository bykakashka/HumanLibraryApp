package com.byka.humanlibrary.fragments.info;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.data.InfoWrapper;
import com.byka.humanlibrary.fragments.SimpleTextFragment;
import com.byka.humanlibrary.provider.InfoPageProvider;

import org.jetbrains.annotations.NotNull;

public abstract class InfoCachedTextFragment extends SimpleTextFragment {
    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = view.findViewById(R.id.simpleFragmentText);

        textView.setTextColor(Color.BLACK);
        getActivity().setTitle(getTitle(getResources()));

        InfoWrapper infoWrapper = getInfoWrapper();
        if (isExecuted() && infoWrapper.getInfo() != null) {
            textView.setText(infoWrapper.getInfo().getText());
        } else {
            setExecuted();

            new InfoPageProvider(textView, infoWrapper).execute(getUrl(getResources()));
        }
    }

    public abstract String getUrl(Resources resources);
    public abstract String getTitle(Resources resources);
    public abstract boolean isExecuted();
    public abstract void setExecuted();
    public abstract InfoWrapper getInfoWrapper();
}
