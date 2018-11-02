package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensumfirebase.Adapter.PensumList_Adapter;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Pensum_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private PensumList_Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pensum_, container, false);

        recyclerView = view.findViewById(R.id.listview_pensum);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new PensumList_Adapter();
        recyclerView.setAdapter(adapter);


        return view;
    }

}
