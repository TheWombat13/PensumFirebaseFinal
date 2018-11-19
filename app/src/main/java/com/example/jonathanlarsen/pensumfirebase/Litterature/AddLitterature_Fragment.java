package com.example.jonathanlarsen.pensumfirebase.Litterature;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.MainActivity;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.LitteratureModel;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.example.jonathanlarsen.pensumfirebase.R;

import static android.app.Activity.RESULT_OK;
import static com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment.pensumView;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.ALLOW_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.MY_PERMISSIONS_REQUEST_CAMERA;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.startInstalledAppDetailsActivity;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class AddLitterature_Fragment extends Fragment implements View.OnClickListener{

    /**
     * Created by Thomas-PC on 27/09/2018.
     */

        static final int PICK_IMAGE = 10;
        static final int CAMERA_REQUEST = 11;

        final String SETTINGS_PERMISSION = "Settings";
        final String PREFERENCES_PERMISSION = "Preferences";

        private int characterCounter;
        private int pages;
        private String title, author, authorYear, period, genre,
                publisher, publishedYear, commentary;
        private String currentPhotoPath;

        private Frame frame;
        private TextRecognizer mTextRecognizer;

        private EditText setTitle, setAuthor, setAuthorYear, setPeriod, setGenre,
                setPages, setPublisher, setPublishedYear, setCommentary;
        private Button save, ScanStorageImg, ScanNewImg;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_add_litterature, container, false);

            setTitle = view.findViewById(R.id.text_name);
            setAuthor = view.findViewById(R.id.text_author);
            setAuthorYear = view.findViewById(R.id.text_author_year);
            setPeriod = view.findViewById(R.id.text_period);
            setGenre = view.findViewById(R.id.text_genre);
            setPages = view.findViewById(R.id.text_pages);
            setPublisher = view.findViewById(R.id.text_publisher);
            setPublishedYear = view.findViewById(R.id.text_published_year);
            setCommentary = view.findViewById(R.id.text_commentary);

            ScanStorageImg = view.findViewById(R.id.ScanStorageImg_Button);
            save = view.findViewById(R.id.save_button);
            ScanNewImg = view.findViewById(R.id.ScanImg_Button);

            startLayout();
            startOnClickListeners();

            return view;
        }

        private void startLayout () {

            ScanNewImg.setText(R.string.btn_camera);
            save.setText(R.string.btn_save);
            ScanStorageImg.setText(R.string.btn_Storage);

            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (MainActivity.getFromPref(getContext(), ALLOW_KEY)) {
                    showPermissionAlert(SETTINGS_PERMISSION);
                } else if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CAMERA)

                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()),
                            Manifest.permission.CAMERA)) {
                        showPermissionAlert(PREFERENCES_PERMISSION);
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                }
            } else {
                ScanNewImg.setClickable(true);
                ScanNewImg.setEnabled(true);
            }
        }

        private void startOnClickListeners() {
            save.setOnClickListener(this);
            ScanStorageImg.setOnClickListener(this);
            ScanNewImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.save_button:
                    Log.d(TAG, "onClick: Save");

                    setVariables();
                    break;

                case R.id.ScanStorageImg_Button:
                    Log.d(TAG, "onClick: Scan image from storage");

                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");

                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");

                    Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                    startActivityForResult(chooserIntent, PICK_IMAGE);
                    break;

                case R.id.ScanImg_Button:
                    Log.d(TAG, "onClick: Scan image from camera");

                    Toast.makeText(getContext(), "Not implemented yet!", Toast.LENGTH_LONG).show();

                    /*
                     * Image seems to take a thumbnail instead of full-size image.
                     * Removed for open test to avoid flooding.
                     *
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (takePictureIntent.resolveActivity(Objects.requireNonNull(getContext()).getPackageManager()) != null) {

                        File photoFile;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            return;
                        }

                        Intent install = new Intent(Intent.ACTION_VIEW);
                        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        if (photoFile != null) {
                            Uri photoURI = null;
                            try {
                                photoURI = FileProvider.getUriForFile(getContext(),
                                        getContext().getApplicationContext().getPackageName() + ".provider",
                                        createImageFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                        }
                    }*/

                    break;

                default:
                    Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                            "Oops! That's not how its suppose to work!", Toast.LENGTH_LONG).show();
                    break;
            }

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);
            Log.d(TAG, "onActivityResult started");

            if (requestCode == PICK_IMAGE || requestCode == CAMERA_REQUEST) {

                if (requestCode == PICK_IMAGE) {Log.d(TAG, "RequestCode received == PICK_IMAGE");}
                if (requestCode == CAMERA_REQUEST) {Log.d(TAG, "RequestCode received == CAMARA_REQUEST");}

                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "ResultCode verified. Starting action");

                    //Resetting any previously scanned pages.
                    this.characterCounter = 0;

                    Uri uri = null;

                    try {
                        uri = intent.getData();
                    } catch (NullPointerException e) {
                        Log.d(TAG, "No image returned!");
                    }
                    try {
                        Bitmap bitmap = null;
                        if (requestCode == PICK_IMAGE) {
                            bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), uri);
                        }

                        //Todo might returns a thumbnail which is not good enough for text recogniztion?
                        if (requestCode == CAMERA_REQUEST) {
                            File imageFile = new File(currentPhotoPath);
                            if (imageFile.exists()) {
                                Log.d(TAG, "File exists");
                                bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                            }
                        }

                        if (bitmap != null) {
                            frame = new Frame.Builder()
                                    .setBitmap(bitmap)
                                    .build();

                            Log.d(TAG, "TextRecognizer initializing");
                            try {
                                new textRecognizer().execute();
                            } catch (Exception e) {
                                Log.d(TAG, "TextRecognizer failed!");
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

            if (setAuthor.getText() != null) {
                this.author = setAuthor.getText().toString();
            } else {
                this.author = "";
            }
            if (setAuthorYear.getText() != null) {
                this.authorYear = setAuthorYear.getText().toString();
            } else {
                this.authorYear = "";
            }
            if (setPeriod.getText() != null) {
                this.period = setPeriod.getText().toString();
            } else {
                this.period = "";
            }
            if (setGenre.getText() != null) {
                this.genre = setGenre.getText().toString();
            } else {
                this.genre = "";
            }
            if (pages != 0) {
                this.pages = Integer.parseInt(setPages.getText().toString());
            } else {
                this.pages = 0;
            }
            if (setPublisher.getText() != null) {
                this.publisher = setPublisher.getText().toString();
            } else {
                this.publisher = "";
            }
            if (setPublishedYear.getText() != null) {
                this.publishedYear = setPublishedYear.getText().toString();
            } else {
                this.publishedYear = "";
            }
            if (setCommentary.getText() != null) {
                this.commentary = setCommentary.getText().toString();
            } else {
                this.commentary = "";
            }
            saveLitterature();

            Log.d(TAG, "Name:" + title +
                    " Author:" + author + " Author year:" + authorYear +
                    " Period:" + period +
                    " Genre:" + genre + " Pages:" + pages +
                    " publisher:" + publisher + " published year:" + publishedYear +
                    " Commentary:" + commentary);
        }

        private void setCharacters(int characters) {
            this.characterCounter = this.characterCounter + characters;
        }

        private int getCharacters() {
            return this.characterCounter;
        }

        private void setScannedPages () {
            this.pages = (getCharacters() * Integer.parseInt(setPages.getText().toString())) / 2400;
        }

        /*
         * Serializing of data to mobile device
         */
        private void saveLitterature() {

            if (this.authorYear.equals("")) {
                this.authorYear = "0000";
            }
            if (this.publishedYear.equals("")) {
                this.publishedYear = "0000";
            }

            LitteratureModel temp = new LitteratureModel(this.title, this.author, this.period,
                    this.genre, this.publisher, Integer.parseInt(this.authorYear),
                    Integer.parseInt(this.publishedYear), this.pages);

            //ToDo get the proper pensumList position
            litteratureListView.get(pensumList.get(pensumView)).add(this.title);
            litteratureData.put(litteratureListView.get(pensumList.get(pensumView)).get(litteratureListView.size()+1), temp);

            try {
                InternalStorage.writeObject(getContext(), LITTERATURE_LIST_OBJECT_KEY, litteratureListView);
                InternalStorage.writeObject(getContext(), LITTERATUREDATA_OBJECT_KEY, litteratureData);
                Log.d(TAG, "Save succeeded");
                closeFragment();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Save failed");
                Toast.makeText(getContext(), "Save failed!", Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, litteratureData.get(litteratureListView.get(
                    pensumList.get(pensumView)).get(litteratureListView.get(pensumList.get(pensumView)).size()-1)).getTitle());
        }

        private void closeFragment() {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                    .popBackStack();
        }

        private File createImageFile() throws IOException {
            // Create an image file title
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Objects.requireNonNull(this.getActivity()).getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",   /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            this.currentPhotoPath = image.getAbsolutePath();
            return image;
        }

        private class textRecognizer extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute () {
                mTextRecognizer =
                        new TextRecognizer.Builder(Objects.requireNonNull(getActivity()).getApplicationContext()).build();
            }

            @Override
            protected String doInBackground(String... strings) {
                Log.d(TAG, "TextRecognizer initialized");

                if (!mTextRecognizer.isOperational()) {
                    Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                            "Oops ! Not able to start the text recognizer ...", Toast.LENGTH_LONG).show();
                } else {
                    SparseArray<TextBlock> textBlocks = mTextRecognizer.detect(frame);

                    Log.d(TAG, "Inspecting textBlock.size(): " + textBlocks.size());

                    if (textBlocks.size() != 0) {
                        for (int i = 0; i < textBlocks.size(); i++) {
                            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));

                            setCharacters(textBlock.getValue().length());
                            Log.d(TAG, textBlock.getValue() +
                                    "| Characters: " + textBlock.getValue().length()
                                    + " | Total Characters: " + getCharacters());
                        }
                    } else {
                        try {
                            Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(),
                                    "Oops ! Something went wrong!", Toast.LENGTH_LONG).show();
                        } catch (RuntimeException e) {
                            Log.d(TAG, "RunTimeException occured in textRecognizer.");
                        }
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getActivity(),
                        "Text Recognition completed!", Toast.LENGTH_SHORT).show();
                setScannedPages();
            }
        }

        //Alert dialogs
         private void getPages () {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            final EditText text = new EditText(getActivity());

            builder.setTitle("Page").setMessage("Enter the amount of pages").setView(text);
            builder.setPositiveButton("Enter",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface di, int i) {
                            final String input = text.getText().toString();
                            Log.d(TAG, "Input number:" + input);
                            if(android.text.TextUtils.isDigitsOnly(input)) {
                                pages = Integer.parseInt(input);
                            } else {
                                //ToDo find better solution!
                                getPages();
                            }
                        }
                    });
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface di, int i) {
                        }
                    });
            builder.create().show();

        }

        private void showAlertMessage (String message) {
            AlertDialog alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getContext()))
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

        private void showPermissionAlert(final String permission) {
            final AlertDialog alertDialog =
                    new AlertDialog.Builder(Objects.requireNonNull(getContext())).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("App needs to access the Camera.");


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            closeFragment();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (permission.equals(PREFERENCES_PERMISSION)) {
                                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
                                        new String[]{Manifest.permission.CAMERA},
                                        MY_PERMISSIONS_REQUEST_CAMERA);
                                ScanNewImg.setClickable(true);
                                ScanNewImg.setEnabled(true);
                            } else if (permission.equals(SETTINGS_PERMISSION)) {
                                startInstalledAppDetailsActivity(getActivity());
                            }
                        }
                    });
            alertDialog.show();
        }

}
