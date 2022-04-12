package com.example.billboardchart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.ListFragment;

interface Listener{
    void itemClicked(int id);
}

public class CategoryListFragment extends ListFragment {
    private Listener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Build the list
        int len = Categories.categories.length;
        String[] names = new String[len];
        for (int i = 0; i < len; i++) {
            names[i] = Categories.categories[i].getName();
        }

        //Populate the list fragment
        setListAdapter(new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, names));


        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(v.getContext(), CategoryActivity.class);
        intent.putExtra("category_id", position);
        startActivity(intent);

    }

}
