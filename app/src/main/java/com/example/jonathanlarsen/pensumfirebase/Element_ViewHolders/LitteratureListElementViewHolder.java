package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Litterature.ViewLitterature_Fragment;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment.recyclerView;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;

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

        Bundle bundle = new Bundle();
        bundle.putInt(LITTERATUREDATA_OBJECT_KEY, getAdapterPosition());

        ViewLitterature_Fragment nextFrag = new ViewLitterature_Fragment();
        nextFrag.setArguments(bundle);

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.MiddleContainer, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onLongClick(View view) {
        Log.d(TAG, "OnLongClick: " + view.getId());

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            LitteratureListElementViewHolder viewHolder =
                    (LitteratureListElementViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            viewHolder.delete.setVisibility(View.VISIBLE);
        }
        Toast.makeText(view.getContext(), "Delete state shown", Toast.LENGTH_SHORT).show();
        return false;
    }
}
