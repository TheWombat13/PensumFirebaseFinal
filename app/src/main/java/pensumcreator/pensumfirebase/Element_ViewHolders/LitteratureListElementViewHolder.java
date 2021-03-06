package pensumcreator.pensumfirebase.Element_ViewHolders;

import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pensumcreator.pensumfirebase.Litterature.ViewLitterature_Fragment;
import pensumcreator.pensumfirebase.R;
import pensumcreator.pensumfirebase.StorageDataModels.InternalStorage;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static pensumcreator.pensumfirebase.Adapter.Litterature_Adapter.deleteState;
import static pensumcreator.pensumfirebase.Litterature.Litterature_Fragment.recyclerView;
import static pensumcreator.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static pensumcreator.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static pensumcreator.pensumfirebase.MainActivity.TAG;
import static pensumcreator.pensumfirebase.MainActivity.item;
import static pensumcreator.pensumfirebase.Pensum.Pensum_Fragment.context;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureData;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureListView;

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
    public void onClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putInt(LITTERATUREDATA_OBJECT_KEY, getAdapterPosition());

        deleteState = false;
        item.setVisible(false);

        ViewLitterature_Fragment nextFrag = new ViewLitterature_Fragment();

        nextFrag.setArguments(bundle);

        nextFrag.setSharedElementEnterTransition(new ChangeBounds().setDuration(200));
        nextFrag.setArguments(bundle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(view, "hej");

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .addSharedElement(view, ViewCompat.getTransitionName(view))
                    .replace(R.id.MiddleContainer, nextFrag)
                    .addToBackStack(null)
                    .commit();

        } else {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MiddleContainer, nextFrag)
                    .addToBackStack(null)
                    .commit();
        }
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
                    Toast.makeText(view.getContext(), "Not Implemented yet!", Toast.LENGTH_SHORT).show();
                    /*if (viewHolder.delete.isChecked()) {
                        //ToDo Find values
                    }*/
                }
                saveState();
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
