package com.byka.humanlibrary.provider;

import android.widget.ArrayAdapter;

import com.byka.humanlibrary.data.ListElement;

public abstract class AbstractStringListProvider<PROCESS, RESULT extends ListElement> extends AbstractListProvider<PROCESS, RESULT, String> {
    public AbstractStringListProvider(ArrayAdapter<String> adapter) {
        super(adapter);
    }

    public String getElementRepresentation(RESULT element) {
        return element.getStringRepresentation();
    }
}
