package com.example.jonathanlarsen.pensumfirebase.Storage_DataModels;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATURE_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUMDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class InternalStorage implements Serializable {

    private InternalStorage () {

    }

    public static void writeObject(Context context, String key, Object object)
            throws IOException {

        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    public static Object readObject(Context context, String key)
            throws IOException, ClassNotFoundException {

        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

    public static void savePensum (Context context) {
        try {
            InternalStorage.writeObject(context, PENSUM_LIST_OBJECT_KEY, pensumList);
            InternalStorage.writeObject(context, PENSUMDATA_OBJECT_KEY, pensumData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Save failed!", Toast.LENGTH_LONG).show();
        }
    }

    public static void saveLitterature (Context context) {
        try {
            InternalStorage.writeObject(context, LITTERATURE_LIST_OBJECT_KEY, litteratureListView);
            InternalStorage.writeObject(context, LITTERATUREDATA_OBJECT_KEY, litteratureData);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Save failed!", Toast.LENGTH_LONG).show();
        }
    }
}