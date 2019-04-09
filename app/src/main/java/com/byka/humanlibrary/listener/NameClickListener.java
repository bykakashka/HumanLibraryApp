package com.byka.humanlibrary.listener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.data.Board;
import com.byka.humanlibrary.fragments.books.BookInfoFragment;

public class NameClickListener {
    private FragmentActivity fragmentActivity;
    public NameClickListener(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public View.OnClickListener getListener(final Board item) {
        return v -> {
            FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            ft.replace(R.id.content_placeholder, BookInfoFragment.newInstance(item.getBookId(), item.getBookName()));
            ft.commit();
        };
    }
}
