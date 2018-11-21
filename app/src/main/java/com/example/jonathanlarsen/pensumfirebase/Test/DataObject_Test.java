package com.example.jonathanlarsen.pensumfirebase.Test;

import android.util.Log;

import com.example.jonathanlarsen.pensumfirebase.R;
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
        pensumList.add("Dansk litteratur efter 1925");
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
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setTeacher("Henrik Andresen");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("Halløj");

        pensumData.put(pensumList.get(1), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTitle(pensumList.get(1));
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPagesToGo(500);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTeacher("Kim Byvald");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("Hej");

        pensumData.put(pensumList.get(2), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTitle(pensumList.get(2));
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPagesToGo(900);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTeacher("JØK");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("Hejsa");

        /*
        Adding the litterature-data & meta-data for each sub litterature for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureModel = new LitteratureModel("Fiskerne", "Kirk, Hans", "", "Epik", "Gyldendahls Tranebøger", R.drawable.img2, 1928, 2005, 271 );
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(0)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Nordkraft", "Ejersbo, Jacob", "", "Epik", "Gyldendahl", R.drawable.arthistory1, 2002, 2007, 346);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(0)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Tryghedsnarkoman", "Andersen, Vita", "", "Lyrik", "Gyldendahl", R.drawable.image_files_books, 1977, 1977, 270);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(0)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Kostym. Norstedts", "Holmqvist, Ninni", "", "Epik", "Gyldendahl", R.drawable.arthistory1, 1995, 1995, 107);
        litteratureListView.get(pensumList.get(0)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(0)+litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test5", "", "", "", "", R.drawable.arthistory1, 1, 1, 2 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test6", "", "", "", "", R.drawable.img2, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test7", "", "", "", "", R.drawable.img2, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test8", "", "", "", "", R.drawable.img2, 1, 1, 2 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test9", "", "", "", "", R.drawable.arthistory1, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test10", "", "", "", "", R.drawable.arthistory1, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test11", "", "", "", "", R.drawable.img2, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test12", "", "", "", "", R.drawable.arthistory1, 1, 2, 3);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test13", "", "", "", "", R.drawable.img2, 1, 1, 2 );
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test14", "", "", "", "", R.drawable.img2, 1, 2, 3);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test15", "", "", "", "", R.drawable.img2, 1, 2, 3);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        Log.d(TAG, "So far so fucking good!");
    }
}
