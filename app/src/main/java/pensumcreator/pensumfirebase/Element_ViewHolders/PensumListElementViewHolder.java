package pensumcreator.pensumfirebase.Element_ViewHolders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pensumcreator.pensumfirebase.Litterature.Litterature_Fragment;
import pensumcreator.pensumfirebase.R;
import pensumcreator.pensumfirebase.StorageDataModels.InternalStorage;

import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static pensumcreator.pensumfirebase.MainActivity.PENSUMDATA_OBJECT_KEY;
import static pensumcreator.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;
import static pensumcreator.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static pensumcreator.pensumfirebase.MainActivity.TAG;
import static pensumcreator.pensumfirebase.Pensum.Pensum_Fragment.context;
import static pensumcreator.pensumfirebase.Pensum.Pensum_Fragment.recyclerView;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.pensumData;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.pensumList;

public class PensumListElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView title;
    public TextView teacher;
    public TextView pages;
    public TextView spacing;
    public TextView pagesToGo;
    //public TextView ADD;
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

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.MiddleContainer, nextFrag,"Litterature_fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onLongClick(View v) {

        pages.setVisibility(View.GONE);
        pagesToGo.setVisibility(View.GONE);
        spacing.setVisibility(View.GONE);
        delete.setVisibility(View.VISIBLE);
        Toast.makeText(v.getContext(), "Hej", Toast.LENGTH_SHORT).show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Ønsker du at slette dette pensum?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        R.string.YES_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d(TAG, "Adapter position: " + getAdapterPosition()
                                        + " PensumList size: " + pensumList.size());
                                pensumList.remove(getAdapterPosition());
                                saveState();

                                if (getAdapterPosition() == 0) {
                                    //recyclerView.getAdapter().notifyItemChanged(getAdapterPosition());
                                } else
                                    recyclerView.getAdapter().notifyItemRangeChanged(getAdapterPosition(), pensumList.size());

                                //ToDO Doesn't work on the first element in pensumList

                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        R.string.NO_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                pages.setVisibility(View.VISIBLE);
                                pagesToGo.setVisibility(View.VISIBLE);
                                spacing.setVisibility(View.VISIBLE);
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

    /*
     * Serializing of data to mobile device
     */
    public void saveState() {
        try {
            InternalStorage.writeObject(context, PENSUM_LIST_OBJECT_KEY, pensumList);
            InternalStorage.writeObject(context, PENSUMDATA_OBJECT_KEY, pensumData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Save failed!", Toast.LENGTH_LONG).show();
        }
    }

}