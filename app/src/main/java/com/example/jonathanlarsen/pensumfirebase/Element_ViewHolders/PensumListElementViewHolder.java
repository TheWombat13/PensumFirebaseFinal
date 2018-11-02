package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;

public class PensumListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView title;
    public TextView teacher;
    public TextView pages;
    public TextView pagesToGo;
    public TextView ADD;


    public PensumListElementViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = new Bundle();
        bundle.putInt(PENSUM_BUNDLE_KEY, getAdapterPosition());

        Litterature_Fragment nextFrag= new Litterature_Fragment();
        nextFrag.setArguments(bundle);

        Toast.makeText(v.getContext(), "OnClick: list-Element", Toast.LENGTH_SHORT).show();

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.MiddleContainer, nextFrag,"findThisFragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public boolean onLongClick(View v) {
        //ToDO
        return false;
    }
}