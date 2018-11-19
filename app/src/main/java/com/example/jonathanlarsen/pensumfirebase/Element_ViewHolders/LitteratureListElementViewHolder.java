package com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Litterature.ViewLitterature_Fragment;
import com.example.jonathanlarsen.pensumfirebase.R;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.Adapter.Litterature_Adapter.deleteState;
import static com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment.recyclerView;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUMDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.item;
import static com.example.jonathanlarsen.pensumfirebase.Pensum.Pensum_Fragment.context;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class LitteratureListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView title;
    public TextView author;
    public TextView period;
    public TextView genre;
    public TextView publisher;
    public TextView writenYear;
    public TextView publishedYear;
    public TextView pages;
    public ImageView image;

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


        nextFrag.setSharedElementEnterTransition(new ChangeBounds().setDuration(2000));

        title.setTransitionName("Title");
        author.setTransitionName("Author");
        pages.setTransitionName("Pages");
        image.setTransitionName("Image");

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.MiddleContainer, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .addSharedElement(title, "Title")
                .addSharedElement(author, "Author")
                .addSharedElement(pages, "Pages")
                .addSharedElement(image, "Image")
                .commit();
    }

    @Override
    public boolean onLongClick(final View view) {
        Log.d(TAG, "OnLongClick: " + view.getId());

        //ToDo doesn't work on viewholder not initially loaded.

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            LitteratureListElementViewHolder viewHolder =
                    (LitteratureListElementViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            viewHolder.delete.setVisibility(View.VISIBLE);
        }
        deleteState = true;
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                    LitteratureListElementViewHolder viewHolder =
                            (LitteratureListElementViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                    if (viewHolder.delete.isChecked()) {
                        //ToDo Find values
                        litteratureListView.remove(1);
                        litteratureData.remove(1);
                    }
                }
                saveState();
                Toast.makeText(view.getContext(), "FUCK YOU!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        item.setVisible(true);

        Toast.makeText(view.getContext(), "Delete state shown", Toast.LENGTH_SHORT).show();
        return true;
    }

    /*
     * Serializing of data to mobile device
     */
    public void saveState() {
        try {
            InternalStorage.writeObject(context, LITTERATURE_LIST_OBJECT_KEY, litteratureListView);
            InternalStorage.writeObject(context, LITTERATUREDATA_OBJECT_KEY, litteratureData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Save failed!", Toast.LENGTH_LONG).show();
        }
    }
}
