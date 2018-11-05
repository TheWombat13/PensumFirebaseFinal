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

    public LitteratureListElementViewHolder vh;
    private int i;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.litteraturelist_item, parent, false);
        vh = new LitteratureListElementViewHolder(view);

        i = litteratureListView.get(pensumList.get(pensumView)).size();

        vh.title = view.findViewById(R.id.title);
        vh.author = view.findViewById(R.id.author);
        vh.period = view.findViewById(R.id.period);
        vh.genre = view.findViewById(R.id.genre);
        vh.publisher = view.findViewById(R.id.publisher);
        vh.publishedYear = view.findViewById(R.id.published_year);
        vh.writenYear = view.findViewById(R.id.written_year);
        vh.pages = view.findViewById(R.id.littarature_pages);

        vh.delete = view.findViewById(R.id.delete_checkbox);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Listview size:" + i + " - Current position:"+ position);

        if (position<i) {
            vh.title.setText(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getTitle());
            vh.author.setText(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getAuthor());
            vh.period.setText(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getPeriod());
            vh.genre.setText(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getGenre());
            vh.publisher.setText(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getPublisher());
            vh.publishedYear.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getPublishedYear()));
            vh.writenYear.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getWritenYear()));
            vh.pages.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                        pensumList.get(pensumView)).get(position)).getPages()));
        }

    }

    @Override
    public int getItemCount() {
        return litteratureListView.get(pensumList.get(pensumView)).size();
    }
}
