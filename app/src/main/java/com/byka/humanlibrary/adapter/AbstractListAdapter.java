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
    private int lastPos = -1;

    public AbstractListAdapter(List<T> data, int elementViewId, Context context) {
        super(context, elementViewId, data);
        this.elementViewId = elementViewId;
    }

    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        initView(position, convertView, parent);
        // Get the data item for this position
        T dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        V viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = newViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(this.elementViewId, parent, false);

            fillViewHolder(viewHolder, convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (V) convertView.getTag();
        }

//        Animation animation = AnimationUtils.loadAnimation(getContext(),
//                (position > lastPos) ? R.anim.load_down_anim : R.anim.load_up_anim);
//        convertView.startAnimation(animation);
        lastPos = position;
        fillView(dataModel, viewHolder);
        // Return the completed view to render on screen
        return convertView;
    }

    protected void initView(int position, View convertView, @NotNull ViewGroup parent) {
        // do nothing
    }

    protected abstract V newViewHolder();

    protected abstract void fillViewHolder(V viewHolder, View view);

    protected abstract void fillView(final T item, final V viewHolder);
}
