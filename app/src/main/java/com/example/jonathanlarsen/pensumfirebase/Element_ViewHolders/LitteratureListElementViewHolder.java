package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LitteratureListElementViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView author;
    public TextView period;
    public TextView genre;
    public TextView publisher;
    public TextView writenYear;
    public TextView publishedYear;
    public TextView pages;

    public CheckBox delete;

    public LitteratureListElementViewHolder(View itemView) {
        super(itemView);
    }
}
