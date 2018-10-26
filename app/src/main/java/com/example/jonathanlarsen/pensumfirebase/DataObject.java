package com.example.jonathanlarsen.pensumfirebase;

import com.example.jonathanlarsen.pensumfirebase.Litterature.LitteratureMetaModel;
import com.example.jonathanlarsen.pensumfirebase.Litterature.LitteratureModel;
import com.example.jonathanlarsen.pensumfirebase.PensumList.PensumModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class DataObject implements Serializable {

    public static List<String> overview;

    /*
    Hashmap <overview, pensumList>
     */
    public static HashMap<String, List<String>> pensumListView;
    public static HashMap<String, PensumModel> pensumData;

    /*
    List with keys for each sub-element of the frontpage-overview
     */
    public static List<String> pensumList;


    /*
    Hashmap <pensumList, litterature>
     */
    public static HashMap<String, List<String>> litteratureListView;
    public static HashMap<String, LitteratureModel> litteratureData;
    public static HashMap<String, LitteratureMetaModel> litteratureMetaData;

    /*
    List with keys for each sub-sub-element of the frontpage-overview OR
    the sub-elements of each pensum
     */
    public static List<String> litterature;

    public DataObject () {
        pensumListView = new HashMap<>();
        pensumData = new HashMap<>();

        litteratureListView = new HashMap<>();
        litteratureData = new HashMap<>();
        litteratureMetaData = new HashMap<>();
    }

}
