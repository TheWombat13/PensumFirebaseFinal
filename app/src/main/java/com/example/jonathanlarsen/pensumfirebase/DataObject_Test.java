package com.example.jonathanlarsen.pensumfirebase;

import android.util.Log;

import com.example.jonathanlarsen.pensumfirebase.Litterature.LitteratureModel;
import com.example.jonathanlarsen.pensumfirebase.PensumList.PensumModel;

import java.util.Objects;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;

public class DataObject_Test extends DataObject {

    private LitteratureModel litteratureModel;

    public DataObject_Test () {
        DataObject_Initialer();
    }

    private void DataObject_Initialer () {
        /*
        Initializing the List to be loaded as frontpage listview with each needed pensum
         */
        pensumList.add(0, "Kunst-Historie");
        pensumList.add(1, "Litteraturhistorie 2");
        pensumList.add(2, "Dansk");

        /*
        Setting headers for each pensum created, here we set the headers as:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureListView.put(pensumList.get(0), litterature.get(pensumList.get(0)));
        litteratureListView.put(pensumList.get(1), litterature.get(pensumList.get(1)));
        litteratureListView.put(pensumList.get(2), litterature.get(pensumList.get(2)));

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
        Adding the litterature list to its parent
        For respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureListView.put(pensumData.get(pensumList.get(0)).getTitle(),
                litterature.get(pensumData.get(pensumList.get(0)).getTitle()));

        litteratureListView.put(pensumData.get(pensumList.get(1)).getTitle(),
                litterature.get(pensumData.get(pensumList.get(1)).getTitle()));

        litteratureListView.put(pensumData.get(pensumList.get(2)).getTitle(),
                litterature.get(pensumData.get(pensumList.get(2)).getTitle()));

        /*
        Adding the litterature-data & meta-data for each sub litterature for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk

            How to get litteratureData:
            pensumListView.get(pensumList.get( - Your pensumList - )).get( - Your Chosen listview - )
         */
        litteratureModel = new LitteratureModel("Test1", "", "", "", "", 1, 1, 2 );
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test2", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test3", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test4", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test5", "", "", "", "", 1, 1, 2 );
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test6", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test7", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test8", "", "", "", "", 1, 1, 2 );
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test9", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test10", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test11", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test12", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Test13", "", "", "", "", 1, 1, 2 );
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test14", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Test15", "", "", "", "", 1, 2, 3);
        litteratureData.put(litteratureModel.getTitle(), litteratureModel);

        Log.d(TAG, "So far so fucking good!");
    }
}
