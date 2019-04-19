package com.byka.humanlibrary.fragments.books;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.provider.BookProvider;

import org.jetbrains.annotations.NotNull;

public class BookInfoFragment extends Fragment {

    public static BookInfoFragment newInstance(Long bookId, String bookName) {
        BookInfoFragment fragment = new BookInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("bookName", bookName);
        bundle.putString("bookId", bookId.toString());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_text_default_bar, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        new BookProvider(view.findViewById(R.id.simpleFragmentText)).execute(getResources().getString(R.string.bookByIdUrl) + getArguments().getString("bookId"));
        getActivity().setTitle(getArguments().getString("bookName"));
    }
}
