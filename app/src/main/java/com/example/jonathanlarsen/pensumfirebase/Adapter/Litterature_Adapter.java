package com.example.jonathanlarsen.pensumfirebase.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders.LitteratureListElementViewHolder;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment.pensumView;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class Litterature_Adapter extends RecyclerView.Adapter {

    private LitteratureListElementViewHolder vh;
    private int i;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.litteraturelist_item, parent, false);
        vh = new LitteratureListElementViewHolder(view);

        i = litteratureListView.get(pensumList.get(pensumView)).size();

        vh.title = view.findViewById(R.id.title);
        vh.author = view.findViewById(R.id.author);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Listview size:" + i + " - Current position:"+ position);

        //ToDo Doesn't add the 4th title. Shows "Test2" instead of "Test3"

        if (position<i) {
            vh.title.setText(litteratureData.get(
                    litteratureListView.get(
                            pensumList.get(pensumView)).get(position)).getTitle());

        }
    }

    @Override
    public int getItemCount() {
        return litteratureListView.get(pensumList.get(pensumView)).size();
    }
}
