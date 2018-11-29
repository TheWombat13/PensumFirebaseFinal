package pensumcreator.pensumfirebase.Test;

import android.util.Log;

import pensumcreator.pensumfirebase.R;
import pensumcreator.pensumfirebase.StorageDataModels.DataObject;
import pensumcreator.pensumfirebase.StorageDataModels.LitteratureModel;
import pensumcreator.pensumfirebase.StorageDataModels.PensumModel;

import java.util.ArrayList;
import java.util.Objects;

import static pensumcreator.pensumfirebase.MainActivity.TAG;

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
        pensumList.add("Den Hvide Kube");
        pensumList.add("Litteraturehistorie 2");

        /*
        Setting headers for each pensum created, here we set the headers as:
            Dansk litteratur efter 1925
            Den Hvide Kube
            Litteraturhistorie 2
         */
        litteratureListView.put(pensumList.get(0), list = new ArrayList<>());
        litteratureListView.put(pensumList.get(1), list = new ArrayList<>());
        litteratureListView.put(pensumList.get(2), list = new ArrayList<>());

        /*
        Applying meta-data for each pensum
        Data model for respectively:
            Dansk litteratur efter 1925
            Den Hvide Kube
            Litteraturhistorie 2
         */
        pensumData.put(pensumList.get(0), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setTitle(pensumList.get(0));
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setPagesToGo(1200);
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setTeacher("Henrik Andresen");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("");

        pensumData.put(pensumList.get(1), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTitle(pensumList.get(1));
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setPagesToGo(800);
        Objects.requireNonNull(pensumData.get(pensumList.get(1))).setTeacher("Sigurd Sigurdson");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("Valgfag");

        pensumData.put(pensumList.get(2), new PensumModel());
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTitle(pensumList.get(2));
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPages(0);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setPagesToGo(400);
        Objects.requireNonNull(pensumData.get(pensumList.get(2))).setTeacher("Ulrikke Ulriksen");
        Objects.requireNonNull(pensumData.get(pensumList.get(0))).setComment("");

        /*
        Adding the litterature-data & meta-data for each sub litterature for respectively:
            Dansk litteratur efter 1925
            Den Hvide Kube
            Litteraturhistorie 2
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


        litteratureModel = new LitteratureModel("Kuratering af Samtidskunst", "Ault, Julie", "", "", "Museet for Samtidskunst", R.drawable.arthistory1, 2011, 2011, 31 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Op Art", "Barret, Cyril", "", "", "London: Studio Vista", R.drawable.img2, 1979, 1979, 49);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("The Language of movement", "Brett, Guy", "", "", "London: Studio Vista", R.drawable.img2, 1968, 1968, 38);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Radical Museology, or, What's 'Contemporary' in Museums of Contemporary Art", "Bishop, Claire", "", "", "London: Koenig Books", R.drawable.img2, 2013, 2013, 11 );
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Når børn møder kultur. En antologi om formidling i børnehøjde", "Christensen, Per B", "", "", "Børnekulturens Netværk", R.drawable.arthistory1, 2006, 2006, 56);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("\"Eye Attack Op Art og Kinetisk Kunst\" in Louisiana Revy: Eye Attack. Vol. 56. Nr. 2.", "Colstrup, Tine", "", "", "København: Gyldendal", R.drawable.arthistory1, 2016, 2016, 35);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("An Introduction to Art Criticism", "Houston, Kerr", "", "", "London: Pearson", R.drawable.img2, 2013, 2013, 27);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("The future is self-organized", "Jakobsen, Jakob", "", "", "", R.drawable.arthistory1, 2005, 2, 4);
        litteratureListView.get(pensumList.get(1)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(1)+litteratureModel.getTitle(), litteratureModel);


        litteratureModel = new LitteratureModel("Den Politiske Kandestøber", "Holberg, Ludvig", "Oplysningen", "Drama", "Dansk Lærerforening", R.drawable.img2, 1723, 1964, 111 );
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Forførerens Dagbog Fortælling Fra Enten Eller", "Kierkegaard, Søren", "Romantik", "Epik", "Det Lille Forlag", R.drawable.img2, 1843, 2007, 125);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        litteratureModel = new LitteratureModel("Gengangere", "Ibsen, Henrik", "DMG", "Drama", "DRAMA", R.drawable.img2, 1881, 2016, 70);
        litteratureListView.get(pensumList.get(2)).add(litteratureModel.getTitle());
        litteratureData.put(pensumList.get(2)+litteratureModel.getTitle(), litteratureModel);

        Log.d(TAG, "So far so fucking good!");
    }
}
