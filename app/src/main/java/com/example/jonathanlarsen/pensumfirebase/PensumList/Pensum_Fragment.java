package com.example.jonathanlarsen.pensumfirebase.PensumList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Litterature.Litterature_Fragment;
import com.example.jonathanlarsen.pensumfirebase.R;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Pensum_Fragment extends Fragment {

    String[] landeArray = {"Danmark", "Norge", "Sverige", "Island", "Færøerne", "Finland",
            "Tyskland", "Østrig", "Belgien", "Holland", "Italien", "Grækenland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand"};
    // Vi laver en arrayliste så vi kan fjerne/indsætte elementer
    ArrayList<String> lande = new ArrayList<>(Arrays.asList(landeArray));



    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pensum_, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.listview_pensum);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.setOnClickListener(this); //FINDES IKKE - i stedet skal man lytte efter onClick på de enkelte vieww
        recyclerView.setAdapter(adapter);


        return view;
    }



    RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListeelemViewholder>() {

        private int count = 0;

        @Override
        public int getItemCount() {
            return lande.size()+1;
        }

        @NonNull
        @Override
        public ListeelemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            ListeelemViewholder vh;
            System.out.print("hej"+count);


            if (count >= lande.size()) {

                View view = getLayoutInflater().inflate(R.layout.pensumlist_item_add, parent, false);
                vh = new ListeelemViewholder(view);

                vh.ADD = (TextView) view.findViewById(R.id.ADD);

                //return vh;
                System.out.print("hej"+count);
                return vh;

            } else {
                View view = getLayoutInflater().inflate(R.layout.pensumlist_item, parent, false);
                vh = new ListeelemViewholder(view);

                vh.title = (TextView) view.findViewById(R.id.title_view);
                vh.teacher = (TextView) view.findViewById(R.id.teacher_view);
                vh.pages = (TextView) view.findViewById(R.id.pages);
                //vh.pagesToGo (TextView) view.findViewById(R.id.);


                //vh.title.setOnClickListener(vh);
                //vh.teacher.setOnClickListener(vh);
                //vh.pages.setOnClickListener(vh);

                count +=1;
                System.out.print("hej"+count);

                return vh;
            }

        }

        @Override
        public void onBindViewHolder(ListeelemViewholder vh, int position) {


            if (position<lande.size()) {
               // vh.title.setText(lande.get(position));
               // final String string = lande.get(position);
              //  onBindViewHolder();
                System.out.println("hej"+lande.size());
            }
/*
            if (position > 0) vh.title.append(" (flyt op)");

            vh.teacher.setText("Land nummer " + position + " på vh@"+Integer.toHexString(vh.hashCode()));
            if (position % 3 == 2) {
                vh.billede.setImageResource(android.R.drawable.ic_menu_delete);
            } else {
                vh.billede.setImageResource(android.R.drawable.ic_delete);
            }
            */
        }


    };




    /**
     * En Viewholder husker forskellige views i et listeelement, sådan at søgninger i viewhierakiet
     * med findViewById() kun behøver at ske EN gang.
     * Se https://developer.android.com/training/material/lists-cards.html
     */
    class ListeelemViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView teacher;
        TextView pages;
        TextView pagesToGo;
        TextView ADD;


        public ListeelemViewholder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            final int position = getAdapterPosition();
            final String landenavn = lande.get(position);
            Toast.makeText(v.getContext(), "Klik på " + landenavn, Toast.LENGTH_SHORT).show();

            Litterature_Fragment nextFrag= new Litterature_Fragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MiddleContainer, nextFrag,"findThisFragment")
                    .addToBackStack(null)
                    .commit();



        }

    }

}
