package com.example.jonathanlarsen.pensumfirebase.Litterature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathanlarsen.pensumfirebase.ExpandebleMenu;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment.pensumView;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.LITTERATUREDATA_OBJECT_KEY;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureData;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.litteratureListView;
import static com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.DataObject.pensumList;

public class ViewLitterature_Fragment extends Fragment {

    private static int litteraturView;

    public TextView title;
    public TextView author;
    public TextView period;
    public TextView genre;
    public TextView publisher;
    public TextView writenYear;
    public TextView publishedYear;
    public TextView pages;

    public ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fargment_view_litterature, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            litteraturView = bundle.getInt(LITTERATUREDATA_OBJECT_KEY, 0);
        }

        title = view.findViewById(R.id.title);
        author = view.findViewById(R.id.author);
        period = view.findViewById(R.id.period);
        genre = view.findViewById(R.id.genre);
        publisher = view.findViewById(R.id.publisher);
        publishedYear = view.findViewById(R.id.published_year);
        writenYear = view.findViewById(R.id.written_year);
        pages = view.findViewById(R.id.littarature_pages);

        image = view.findViewById(R.id.image);

        title.setText(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getTitle());
        author.setText(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getAuthor());
        period.setText(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getPeriod());
        genre.setText(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getGenre());
        publisher.setText(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getPublisher());
        publishedYear.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getPublishedYear()));
        writenYear.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getWritenYear()));
        pages.setText(String.valueOf(litteratureData.get(litteratureListView.get(
                pensumList.get(pensumView)).get(litteraturView)).getPages()));



        title.setTransitionName("Title");
        author.setTransitionName("Author");
        pages.setTransitionName("Pages");
        image.setTransitionName("Image");


        return view;
    }
}
