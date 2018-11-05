package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Adapter.PensumList_Adapter;
import com.example.jonathanlarsen.pensumfirebase.R;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;

import java.io.IOException;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUMDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;


public class Pensum_Fragment extends Fragment {

    public static Context context;
    public static RecyclerView recyclerView;
    private PensumList_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_pensum_, container, false);

        context = getContext();

        recyclerView = view.findViewById(R.id.listview_pensum);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new PensumList_Adapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.pensum_divider));


        return view;
    }


    
}
