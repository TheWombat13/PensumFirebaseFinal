package com.example.jonathanlarsen.pensumfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders.PensumListElementViewHolder;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.pensumList;

public class PensumList_Adapter extends RecyclerView.Adapter {

    private PensumListElementViewHolder vh;
    private int pages;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pensumlist_item, parent, false);
        vh = new PensumListElementViewHolder(view);

        vh.title = view.findViewById(R.id.title_view);
        vh.teacher = view.findViewById(R.id.teacher_view);
        vh.pages = view.findViewById(R.id.pages);
        vh.spacing = view.findViewById(R.id.spacing);
        vh.pagesToGo = view.findViewById(R.id.pagesToGo);
        vh.delete = view.findViewById(R.id.deletebtn);
        vh.delete.setVisibility(View.GONE);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        setPages(position);

        if (position<=pensumList.size()) {

            if(position<pensumList.size()) {
                vh.title.setText(pensumList.get(position));
                vh.teacher.setText(pensumData.get(pensumList.get(position)).getTeacher());
                vh.pages.setText(String.valueOf(this.pages));
                vh.pagesToGo.setText(String.valueOf(pensumData.get(pensumList.get(position)).getPagesToGo()));
            }

            /*
             * Used in previous version where the floating action button was not implemented
             */
            /*if (position == pensumList.size()) {
                vh.title.setText("ADD");
            }*/
        }
    }

    @Override
    public int getItemCount() {
        return pensumList.size();
    }

    private void setPages(int position) {
        this.pages = 0;

        if (litteratureListView.get(pensumList.get(position)) != null)
            for (int i = 0; i < litteratureListView.get(pensumList.get(position)).size(); i++) {
                this.pages += litteratureData.get(pensumList.get(position)+litteratureListView.get(pensumList.get(position)).get(i)).getPages();
            }
    }
}
