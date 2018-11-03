package com.example.jonathanlarsen.pensumfirebase.Test;

import android.util.Log;

import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.LitteratureModel;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.PensumModel;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;

public class DataObject_Test extends DataObject {

    private LitteratureModel litteratureModel;
    private ArrayList <String> list;

    public DataObject_Test () {
        DataObject_Initialer();
    }

    private void DataObject_Initialer () {
        /*
        Initializing the List to be loaded as frontpage listview with each needed pensum
         */
        pensumList.add("Kunst-Historie");
        pensumList.add("Litteraturhistorie 2");
        pensumList.add("Dansk");

        /*
        Setting headers for each pensum created, here we set the headers as:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureListView.put(pensumList.get(0), list = new ArrayList<>());
        litteratureListView.put(pensumList.get(1), list = new ArrayList<>());
        litteratureListView.put(pensumList.get(2), list = new ArrayList<>());

        /*
        Applying meta-data for each pensum
        Data model for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        pensumData.put(pensumList.get(0), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setTitle(pensumList.get(0));
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setPagesToGo(1200);
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setTeacher("Anders Andersen");

        pensumData.put(pensumList.get(1), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTitle(pensumList.get(1));
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPagesToGo(500);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTeacher("Kim Byvald");

        pensumData.put(pensumList.get(2), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTitle(pensumList.get(2));
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPagesToGo(900);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTeacher("JØK");

        /*
        Adding the litterature-data & meta-data for each sub litterature for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureModel = new LitteratureModel("Test1", "Dan Jørgensen", "Romantikken", "Lyrik", "Gyldendahl", 1, 1, 2 );
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureListView.get(pensumList.get(0)).get(0), litteratureModel);

        litteratureModel = new LitteratureModel("Test2", "asdf", "asdf", "asdf", "sadf", 1, 2, 3);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureListView.get(pensumList.get(0)).get(1), litteratureModel);

        litteratureModel = new LitteratureModel("Test3", "sad", "asdf", "asfd", "asdf", 1, 2, 3);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureListView.get(pensumList.get(0)).get(2), litteratureModel);

        litteratureModel = new LitteratureModel("Test4", "asf", "asdf", "sadf", "sadf", 1, 2, 3);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureListView.get(pensumList.get(0)).get(3), litteratureModel);


        litteratureModel = new LitteratureModel("Test5", "", "", "", "", 1, 1, 2 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test6", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test7", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test8", "", "", "", "", 1, 1, 2 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test9", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test10", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test11", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test12", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test13", "", "", "", "", 1, 1, 2 );
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test14", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test15", "", "", "", "", 1, 2, 3);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        Log.d(TAG, "So far so fucking good!");
    }
}
