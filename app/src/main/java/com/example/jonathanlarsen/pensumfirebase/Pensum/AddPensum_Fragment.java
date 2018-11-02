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

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;

public class AddPensum_Fragment extends Fragment implements View.OnClickListener{

    /**
     * Created by Thomas-PC on 27/09/2018.
     */

    private String title, teacher;

    private EditText setTitle, setTeacher;
    private Button save;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_pensum, container, false);

        setTitle = view.findViewById(R.id.text_name);
        setTeacher = view.findViewById(R.id.text_author);

        save = view.findViewById(R.id.save_button);

        startLayout();
        startOnClickListeners();

        return view;
    }

    private void startLayout () {

    }

    private void startOnClickListeners() {
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_button:
                Log.d(TAG, "onClick: Save");

                setVariables();
                if (!this.title.equals("")) {
                    closeFragment();
                } else {
                    showAlertMessage("nameError");
                }
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), "Oops! That's not how its suppose to work!", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void setVariables() {
        this.title = setTitle.getText().toString();
        this.teacher = setTeacher.getText().toString();

        Log.d(TAG, "Name:" + title +
                " Author:" + teacher);
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
            case "textRecognizerError":

                alertDialog.setMessage(getText(R.string.errorTextRecognizer));
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
