package pensumcreator.pensumfirebase.StorageDataModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataObject implements Serializable {

    /*
    PensumList is shown on the frontpage as headers for each cell
    Cell data is gathered from pensumData.
     */
    public static List<String> pensumList;
    public static HashMap<String, PensumModel> pensumData;

    /*
    litteratureListView Hashmap <pensumList, litterature>

    List with keys for each sub-element of the frontpage-pensumList OR
    the sub-elements of each pensum
     */
    public static HashMap<String, List<String>> litteratureListView;
    public static HashMap<String, LitteratureModel> litteratureData;

    public DataObject () {
        pensumList = new ArrayList<>();
        pensumData = new HashMap<>();

        litteratureListView = new HashMap<>();
        litteratureData = new HashMap<>();
    }

}
