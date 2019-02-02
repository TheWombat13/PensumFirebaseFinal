package pensumcreator.pensumfirebase;

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
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import pensumcreator.pensumfirebase.Pensum.Pensum_Fragment;
import pensumcreator.pensumfirebase.StorageDataModels.DataObject;
import pensumcreator.pensumfirebase.StorageDataModels.InternalStorage;
import pensumcreator.pensumfirebase.StorageDataModels.LitteratureModel;
import pensumcreator.pensumfirebase.StorageDataModels.PensumModel;
import pensumcreator.pensumfirebase.Test.DataObject_Test;

import static pensumcreator.pensumfirebase.Adapter.Litterature_Adapter.deleteState;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureData;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.litteratureListView;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.pensumData;
import static pensumcreator.pensumfirebase.StorageDataModels.DataObject.pensumList;

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


    private static final String First_launch = "IsItFirstLaunch";
    private static final String Pref_file_name = "PensumCreator";



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
    public static final String PENSUMDATA_OBJECT_KEY = "PENSUMDATA_DATAOBJECT";
    public static final String LITTERATURE_LIST_OBJECT_KEY = "LITTERATURELIST_DATAOBJECT";
    public static final String LITTERATUREDATA_OBJECT_KEY = "LITTERATUREDATA_DATAOBJECT";

    public static final String PENSUM_BUNDLE_KEY = "CURRENT_POSITION";

    /*
     *
     */
    public static String pensum_expand_key = "something";

    /*
     * Toolbar support
     */
    public Toolbar toolbar;
    public static MenuItem item;

    private Fragment pensumlist_fragment, expanded_pensum;
    private String choosen_pensum_to_expand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Toolbar support
         */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().show();

        if (!getIsFirstTimeLaunch(this, First_launch)) {
            Test_disabled = true;
            pensumlist_fragment = new Pensum_Fragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.MiddleContainer, pensumlist_fragment).
                    commit();
        } else {
            setFirstTimeLaunch(this, First_launch, false);
            pensumlist_fragment = new Pensum_Fragment();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.MiddleContainer, pensumlist_fragment).
                    commit();

        }

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

        //ToDo What is this suppose to do?
        Bundle bundle = new Bundle();
        bundle.putString(choosen_pensum_to_expand, pensum_expand_key);




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

    public void setFirstTimeLaunch (MainActivity context, String key, Boolean isFirstTime) {
        SharedPreferences myPrefs = context.getSharedPreferences(Pref_file_name,
                context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, isFirstTime);
        prefsEditor.commit();
    }

    public boolean getIsFirstTimeLaunch(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(Pref_file_name,
                context.MODE_PRIVATE);
        return myPrefs.getBoolean(First_launch, true);
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

    /*
     * Toolbar support
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        item = menu.findItem(R.id.action_delete);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    //end

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (deleteState) {
            deleteState = false;
            item.setVisible(false);
            updateLitteratureAdapter();
        } else
            super.onBackPressed();
    }

    public void updateLitteratureAdapter () {
        Fragment frg = getSupportFragmentManager().findFragmentByTag("Litterature_fragment");
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
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
