package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LitteratureListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView title;
    public TextView author;


    public LitteratureListElementViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        }

    /*
    * onLongClick for enabling delete and possibly editing.
    */
    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
