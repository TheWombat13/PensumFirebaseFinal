package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class LitteratureListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        }

    /*
    * onLongClick for enabling delete and possibly editing.
    */
    @Override
    public boolean onLongClick(View v) {

        //ToDo er kun synlig på en celle ad gangen! Visibility på checkbox er sat i xml pt.
        delete.setVisibility(View.VISIBLE);

        Toast.makeText(v.getContext(), "Hej", Toast.LENGTH_SHORT).show();


        return true;
    }
}
