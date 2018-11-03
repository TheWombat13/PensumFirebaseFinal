package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class PensumListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


    RecyclerView.Adapter adapter;

    public TextView title;
    public TextView teacher;
    public TextView pages;
    public TextView pagesToGo;
    public TextView ADD;
    public ImageButton delete;



    public PensumListElementViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
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

        delete.setVisibility(View.VISIBLE);
        Toast.makeText(v.getContext(), "Hej", Toast.LENGTH_SHORT).show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "delete " +pensumList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Ønsker du at slette dette pensum?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ja",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                pensumList.remove(getAdapterPosition());
                                //ToDO opdaterer ikke adapter
                                //adapter.notifyItemRemoved(getAdapterPosition());
                                //adapter.notifyItemRangeChanged(getAdapterPosition(),pensumList.size());
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Nej",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                delete.setVisibility(View.GONE);
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        return true;

    }

}