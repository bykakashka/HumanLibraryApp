package com.byka.humanlibrary.fragments.books;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.byka.humanlibrary.R;
import com.byka.humanlibrary.adapter.BookListAdapter;
import com.byka.humanlibrary.data.Book;
import com.byka.humanlibrary.fragments.SimpleTextFragment;
import com.byka.humanlibrary.provider.EventBooksProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CatalogFragment extends Fragment {
    public static CatalogFragment newInstance(Long eventId) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("eventId", eventId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catalog, parent, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        BookListAdapter adapter = new BookListAdapter(new ArrayList<>(), getContext());
        TextView emptyCatalog = view.findViewById(R.id.emptyCatalog);

        new EventBooksProvider(adapter, emptyCatalog, getActivity().findViewById(R.id.progressBar)).execute(getResources().getString(R.string.eventCatalogUrl) + getArguments().getLong("eventId"));
        ListView listView = view.findViewById(R.id.booksList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Book book = adapter.getItem(position);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack( "tag" ); // TODO
            ft.replace(R.id.content_placeholder, BookInfoFragment.newInstance(book.getId(), book.getName()));
            ft.commit();
        });
        getActivity().setTitle(getResources().getString(R.string.eventCatalog_title));
    }
}
