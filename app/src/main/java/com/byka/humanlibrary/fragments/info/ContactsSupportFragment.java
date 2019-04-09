package com.byka.humanlibrary.fragments.info;

import android.content.res.Resources;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.data.InfoWrapper;


public class ContactsSupportFragment extends InfoCachedTextFragment {
    private static boolean isExecuted = false;
    private static InfoWrapper infoWrapper;

    @Override
    public String getUrl(Resources resources) {
        return resources.getString(R.string.infoPageUrl) + "CONTACTS_SUPPORT";
    }

    @Override
    public String getTitle(Resources resources) {
        return resources.getString(R.string.support_title);
    }

    @Override
    public boolean isExecuted() {
        return isExecuted;
    }

    @Override
    public void setExecuted() {
        isExecuted = true;
    }

    @Override
    public InfoWrapper getInfoWrapper() {
        if (infoWrapper == null) {
            infoWrapper = new InfoWrapper();
        }

        return infoWrapper;
    }
}
