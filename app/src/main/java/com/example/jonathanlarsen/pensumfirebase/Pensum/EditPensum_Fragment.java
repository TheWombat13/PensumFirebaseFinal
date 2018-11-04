package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.R;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;

public class EditPensum_Fragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_litterature, container, false);

        startLayout();
        startOnClickListeners();

        return view;
    }

    private void startLayout () {

    }

    private void startOnClickListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    /*
     * Serializing of data to mobile device
     */
    private void saveLitterature () {
        try {
            InternalStorage.writeObject(getContext(), LITTERATURE_LIST_OBJECT_KEY, litteratureListView);
            InternalStorage.writeObject(getContext(), LITTERATUREDATA_OBJECT_KEY, litteratureData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Save failed!", Toast.LENGTH_LONG);
        }
    }
}
