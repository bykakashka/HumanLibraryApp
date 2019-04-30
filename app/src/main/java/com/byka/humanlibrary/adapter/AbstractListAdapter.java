package com.byka.humanlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractListAdapter<T, V extends ViewHolder> extends ArrayAdapter<T> {

    private int elementViewId;

    public AbstractListAdapter(List<T> data, int elementViewId, Context context) {
        super(context, elementViewId, data);
        this.elementViewId = elementViewId;
    }

    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        initView(position, convertView, parent);

        T dataModel = getItem(position);

        V viewHolder;

        if (convertView == null) {
            viewHolder = newViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(this.elementViewId, parent, false);

            fillViewHolder(viewHolder, convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (V) convertView.getTag();
        }

        fillView(dataModel, viewHolder);
        return convertView;
    }

    protected void initView(int position, View convertView, @NotNull ViewGroup parent) {
        // do nothing
    }

    protected abstract V newViewHolder();

    protected abstract void fillViewHolder(V viewHolder, View view);

    protected abstract void fillView(final T item, final V viewHolder);
}
