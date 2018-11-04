package com.example.jonathanlarsen.pensumfirebase;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.example.jonathanlarsen.pensumfirebase.Pensum.Pensum_Fragment;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.LitteratureModel;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.PensumModel;
import com.example.jonathanlarsen.pensumfirebase.Test.DataObject_Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class MainActivity extends AppCompatActivity {

    /*
     * Test key. Value needs to be set for testing with predetermined data.
     *      True == live version
     *      False == Test version
     * & Variables for testing and logging.
     */
    private Boolean Test_disabled = false;

    public static final String TAG = "LOG_TAG";
    DataObject data;

    /*
     * Camera keys for permission to use the camera.
     */
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "CAMERA_PREF";


    /*
     * Various keys for accessing data
     */
    public static final String PENSUM_LIST_OBJECT_KEY = "PENSUMLIST_DATAOBJECT";
    public static final String PENSUMDATA_OBJECT_KEY = "PENSUMLIST_DATAOBJECT";
    public static final String LITTERATURE_LIST_OBJECT_KEY = "PENSUMLIST_DATAOBJECT";
    public static final String LITTERATUREDATA_OBJECT_KEY = "PENSUMLIST_DATAOBJECT";

    public static final String PENSUM_BUNDLE_KEY = "CURRENT_POSITION";

    /*
     *
     */
    public static String pensum_expand_key = "something";

    private Fragment pensumlist_fragment, expanded_pensum;
    private String choosen_pensum_to_expand;

    private static Context mContext;
    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext1) {
        mContext = mContext1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Boolean to determine data & storage.
         */
        if (Test_disabled) {
            data = new DataObject();
            try {
                pensumList = (List<String>)
                        InternalStorage.readObject(this, PENSUM_LIST_OBJECT_KEY);
                pensumData = (HashMap<String, PensumModel>)
                        InternalStorage.readObject(this, PENSUMDATA_OBJECT_KEY);
                litteratureListView = (HashMap<String, List<String>>)
                        InternalStorage.readObject(this, LITTERATURE_LIST_OBJECT_KEY);
                litteratureData = (HashMap<String, LitteratureModel>)
                        InternalStorage.readObject(this, LITTERATUREDATA_OBJECT_KEY);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            data = new DataObject_Test();
        }

        Bundle bundle = new Bundle();
        bundle.putString(choosen_pensum_to_expand, pensum_expand_key);

        pensumlist_fragment = new Pensum_Fragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.MiddleContainer, pensumlist_fragment).
                commit();

    }

    /*
     * Shared preferences for saving camera permission
     */
    public static void saveToPreferences(MainActivity context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }

        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //ToDo Look through this.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, permission);

                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            MainActivity.saveToPreferences(MainActivity.this, ALLOW_KEY, true);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
