package pensumcreator.pensumfirebase.Pensum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pensumcreator.pensumfirebase.R;
import pensumcreator.pensumfirebase.StorageDataModels.InternalStorage;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static pensumcreator.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static pensumcreator.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureData;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureListView;

public class EditPensum_Fragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_litterature, container, false);

        startLayout();
        startOnClickListeners();

        return view;
    }

    private void startLayout () {

    }

    private void startOnClickListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    /*
     * Serializing of data to mobile device
     */
    private void saveLitterature () {
        try {
            InternalStorage.writeObject(getContext(), LITTERATURE_LIST_OBJECT_KEY, litteratureListView);
            InternalStorage.writeObject(getContext(), LITTERATUREDATA_OBJECT_KEY, litteratureData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Save failed!", Toast.LENGTH_LONG).show();
        }
    }
}
