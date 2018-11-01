package com.example.jonathanlarsen.pensumfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders.PensumListElementViewHolder;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class PensumList_Adapter extends RecyclerView.Adapter {

    private PensumListElementViewHolder vh;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pensumlist_item, parent, false);
        vh = new PensumListElementViewHolder(view);

        vh.title = view.findViewById(R.id.title_view);
        vh.teacher = view.findViewById(R.id.teacher_view);
        vh.pages = view.findViewById(R.id.pages);
        //ToDo: vh.pagesToGo = view.findViewById(R.id.);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position<=pensumList.size()) {
            if(position<pensumList.size()) {
                vh.title.setText(pensumList.get(position));
                vh.teacher.setText(pensumData.get(pensumList.get(position)).getTeacher());
                vh.pages.setText(String.valueOf(pensumData.get(pensumList.get(position)).getPages()));
            }
            if (position == pensumList.size()) {
                vh.title.setText("ADD");
            }
        }
    }

    @Override
    public int getItemCount() {
        return pensumList.size()+1;
    }
}
