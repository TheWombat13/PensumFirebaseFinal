package com.example.jonathanlarsen.pensumfirebase;

import com.example.jonathanlarsen.pensumfirebase.Litterature.LitteratureModel;
import com.example.jonathanlarsen.pensumfirebase.PensumList.PensumModel;

import java.util.Objects;

public class DataObject_Test extends DataObject {

    private LitteratureModel litteratureModel;

    public DataObject_Test () {
        DataObject_Initialer();
    }

    private void DataObject_Initialer () {
        overview.add(0, "Kunst-Historie");
        overview.add(1, "Litteraturhistorie 2");
        overview.add(2, "Dansk");

        pensumListView.put(overview.get(0), pensumList.get(overview.get(0)));
        pensumListView.put(overview.get(1), pensumList.get(overview.get(1)));
        pensumListView.put(overview.get(2), pensumList.get(overview.get(2)));

        pensumData.put(overview.get(0), new PensumModel());
        pensumData.put(overview.get(1), new PensumModel());
        pensumData.put(overview.get(2), new PensumModel());

        /*
        Data model for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        Objects.requireNonNull(pensumData.get(overview.get(0))).setTitle(overview.get(0));
        Objects.requireNonNull(pensumData.get(overview.get(0))).setPages(0);
        Objects.requireNonNull(pensumData.get(overview.get(0))).setPagesToGo(1200);
        Objects.requireNonNull(pensumData.get(overview.get(0))).setTeacher("Anders Andersen");

        Objects.requireNonNull(pensumData.get(overview.get(1))).setTitle(overview.get(1));
        Objects.requireNonNull(pensumData.get(overview.get(1))).setPages(0);
        Objects.requireNonNull(pensumData.get(overview.get(1))).setPagesToGo(500);
        Objects.requireNonNull(pensumData.get(overview.get(1))).setTeacher("Kim Byvald");

        Objects.requireNonNull(pensumData.get(overview.get(2))).setTitle(overview.get(2));
        Objects.requireNonNull(pensumData.get(overview.get(2))).setPages(0);
        Objects.requireNonNull(pensumData.get(overview.get(2))).setPagesToGo(900);
        Objects.requireNonNull(pensumData.get(overview.get(2))).setTeacher("JØK");

        /*
        Adding the litterature-data & meta-data for each sub litterature for respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureModel = new LitteratureModel("Test1", "", "", "", "", 1, 1, 2 );
        litteratureData.put(pensumListView.get(overview.get(0)).get(0), litteratureModel);

        litteratureModel = new LitteratureModel("Test2", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(0)).get(1), litteratureModel);

        litteratureModel = new LitteratureModel("Test3", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(0)).get(2), litteratureModel);

        litteratureModel = new LitteratureModel("Test4", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(0)).get(3), litteratureModel);


        litteratureModel = new LitteratureModel("Test5", "", "", "", "", 1, 1, 2 );
        litteratureData.put(pensumListView.get(overview.get(1)).get(0), litteratureModel);

        litteratureModel = new LitteratureModel("Test6", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(1), litteratureModel);

        litteratureModel = new LitteratureModel("Test7", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(2), litteratureModel);

        litteratureModel = new LitteratureModel("Test8", "", "", "", "", 1, 1, 2 );
        litteratureData.put(pensumListView.get(overview.get(1)).get(3), litteratureModel);

        litteratureModel = new LitteratureModel("Test9", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(4), litteratureModel);

        litteratureModel = new LitteratureModel("Test10", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(5), litteratureModel);

        litteratureModel = new LitteratureModel("Test11", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(6), litteratureModel);

        litteratureModel = new LitteratureModel("Test12", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(1)).get(7), litteratureModel);


        litteratureModel = new LitteratureModel("Test13", "", "", "", "", 1, 1, 2 );
        litteratureData.put(pensumListView.get(overview.get(2)).get(0), litteratureModel);

        litteratureModel = new LitteratureModel("Test14", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(2)).get(1), litteratureModel);

        litteratureModel = new LitteratureModel("Test15", "", "", "", "", 1, 2, 3);
        litteratureData.put(pensumListView.get(overview.get(2)).get(2), litteratureModel);

        /*
        Adding the litterature to its factions list for respectively
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litterature.get(pensumData.get(overview.get(0)).getTitle()).add(0,
                litteratureData.get(pensumListView.get(overview.get(0)).get(0)).getTitle());

        litterature.get(pensumData.get(overview.get(0)).getTitle()).add(1,
                litteratureData.get(pensumListView.get(overview.get(0)).get(1)).getTitle());

        litterature.get(pensumData.get(overview.get(0)).getTitle()).add(2,
                litteratureData.get(pensumListView.get(overview.get(0)).get(2)).getTitle());

        litterature.get(pensumData.get(overview.get(0)).getTitle()).add(3,
                litteratureData.get(pensumListView.get(overview.get(0)).get(3)).getTitle());


        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(0,
                litteratureData.get(pensumListView.get(overview.get(1)).get(0)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(1,
                litteratureData.get(pensumListView.get(overview.get(1)).get(1)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(2,
                litteratureData.get(pensumListView.get(overview.get(1)).get(2)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(3,
                litteratureData.get(pensumListView.get(overview.get(1)).get(3)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(4,
                litteratureData.get(pensumListView.get(overview.get(1)).get(4)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(5,
                litteratureData.get(pensumListView.get(overview.get(1)).get(5)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(6,
                litteratureData.get(pensumListView.get(overview.get(1)).get(6)).getTitle());

        litterature.get(pensumData.get(overview.get(1)).getTitle()).add(7,
                litteratureData.get(pensumListView.get(overview.get(1)).get(7)).getTitle());


        litterature.get(pensumData.get(overview.get(2)).getTitle()).add(0,
                litteratureData.get(pensumListView.get(overview.get(2)).get(0)).getTitle());

        litterature.get(pensumData.get(overview.get(2)).getTitle()).add(1,
                litteratureData.get(pensumListView.get(overview.get(2)).get(1)).getTitle());

        litterature.get(pensumData.get(overview.get(2)).getTitle()).add(2,
                litteratureData.get(pensumListView.get(overview.get(2)).get(2)).getTitle());



        /*
        Adding the litterature list to its root
        For respectively:
            Kunst-historie
            Litteraturhistorie 2
            Dansk
         */
        litteratureListView.put(pensumData.get(overview.get(0)).getTitle(),
                litterature.get(pensumData.get(overview.get(0)).getTitle()));

        litteratureListView.put(pensumData.get(overview.get(1)).getTitle(),
                litterature.get(pensumData.get(overview.get(1)).getTitle()));

        litteratureListView.put(pensumData.get(overview.get(2)).getTitle(),
                litterature.get(pensumData.get(overview.get(2)).getTitle()));
    }
}
