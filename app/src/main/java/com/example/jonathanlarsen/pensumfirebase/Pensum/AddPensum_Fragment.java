package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.R;
import com.example.jonathanlarsen.pensumfirebase.StorageDataModels.InternalStorage;
import com.example.jonathanlarsen.pensumfirebase.StorageDataModels.PensumModel;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUMDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.StorageDataModels.DataObject.pensumList;

public class AddPensum_Fragment extends Fragment implements View.OnClickListener{

    /**
     * Created by Thomas-PC on 27/09/2018.
     */

    private String title, teacher, comment;
    private int pagesToGo;

    private EditText setTitle, setTeacher, setPagesToGo, setComment;
    private Button save, cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pensum, container, false);

        setTitle = view.findViewById(R.id.text_pensumtitle);
        setTeacher = view.findViewById(R.id.text_pensumauther);
        setPagesToGo = view.findViewById(R.id.text_pensumpages);
        setComment = view.findViewById(R.id.text_pensumcomment);

        save = view.findViewById(R.id.save_button);
        cancel = view.findViewById(R.id.cancel_button);

        startLayout();
        startOnClickListeners();

        return view;
    }

    private void startLayout () {

    }

    private void startOnClickListeners() {
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_button:
                Log.d(TAG, "onClick: Save");
                setVariables();
                break;

            case  R.id.cancel_button:
                closeFragment();
                break;

            default:
                Toast.makeText(getActivity().getApplicationContext(), "Oops! That's not how its suppose to work!", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void setVariables() {

        if (setTitle.getText() == null || setTitle.getText().toString().equals("")) {
            setTitle.setHighlightColor(getResources().getColor(R.color.colorError));
            setTitle.setHintTextColor(getResources().getColor(R.color.colorError));
            setTitle.setError("Skal udfyldes!");
            setTitle.requestFocus();
            return;
        } else {
            this.title = setTitle.getText().toString();
        }

        if (setTeacher.getText() == null || setTeacher.getText().toString().equals("")) {
            this.teacher = "Ingen underviser.";
        } else {
            this.teacher = setTeacher.getText().toString();
        }

        if (setPagesToGo.getText() == null || setPagesToGo.getText().toString().equals("")) {
            Log.d(TAG, "number failed");
            setPagesToGo.setHighlightColor(getResources().getColor(R.color.colorError));
            setPagesToGo.setHintTextColor(getResources().getColor(R.color.colorError));
            setPagesToGo.setError("Skal udfyldes!");
            setPagesToGo.requestFocus();
            return;
        } else {
            try {
                this.pagesToGo = Integer.parseInt(setPagesToGo.getText().toString());
            } catch (NumberFormatException e) {
                Log.d(TAG, "" + e);
                setPagesToGo.setHighlightColor(getResources().getColor(R.color.colorError));
                setPagesToGo.setHintTextColor(getResources().getColor(R.color.colorError));
                setPagesToGo.setError("Skal være et tal!");
                setPagesToGo.requestFocus();
                return;
            }
        }

        this.comment = setComment.getText().toString();

        savePensum();
        closeFragment();
    }

    /*
     * Serializing of data to mobile device
     */
    private void savePensum() {

        ArrayList <String> list;
        PensumModel temp = new PensumModel(this.title, this.teacher, this.comment, 0 , this.pagesToGo);
        //ToDo get the proper pensumList position

        pensumList.add(this.title);
        pensumData.put(pensumList.get(pensumList.size()-1), temp);
        litteratureListView.put(pensumList.get(pensumList.size()-1), list = new ArrayList<>());

        try {
            InternalStorage.writeObject(getContext(), PENSUM_LIST_OBJECT_KEY, pensumList);
            InternalStorage.writeObject(getContext(), PENSUMDATA_OBJECT_KEY, pensumData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Save failed!", Toast.LENGTH_LONG).show();
        }
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager()
                .popBackStack();
    }

    private void showAlertMessage (String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .create();
        alertDialog.setTitle(R.string.errorTitle);

        switch (message) {
            case "nameError":
                alertDialog.setMessage(getText(R.string.errorNameMissing));
                break;
            default:
                alertDialog.setMessage("Error didn't get catched!");
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

}
