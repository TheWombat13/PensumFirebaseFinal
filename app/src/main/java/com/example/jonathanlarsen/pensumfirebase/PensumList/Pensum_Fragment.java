package com.example.jonathanlarsen.pensumfirebase.PensumList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import static android.content.ContentValues.TAG;


public class Pensum_Fragment extends Fragment {

    String[] landeArray = {"Danmark", "Norge", "Sverige", "Island", "Færøerne", "Finland",
            "Tyskland", "Østrig", "Belgien", "Holland", "Italien", "Grækenland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand"};
    // Vi laver en arrayliste så vi kan fjerne/indsætte elementer
    ArrayList<String> lande = new ArrayList<>(Arrays.asList(landeArray));

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = new RecyclerView(this.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //recyclerView.setOnItemClickListener(this); FINDES IKKE - i stedet skal man lytte efter onClick på de enkelte vieww
        recyclerView.setAdapter(adapter);

        //setContentView(recyclerView);
        Snackbar.make(recyclerView, "Tryk en titel for at flytte et element til toppen " +
                "eller på billedet for at fjerne det", Snackbar.LENGTH_INDEFINITE).show();
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListeelemViewholder>() {
        @Override
        public int getItemCount()  {
            return lande.size();
        }

        @Override
        public ListeelemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.pensumlist_item, parent, false);
            ListeelemViewholder vh = new ListeelemViewholder(view);


            vh.title =  (TextView) view.findViewById(R.id.title_view);
            vh.teacher = (TextView) view.findViewById(R.id.teacherView);
            vh.pages = (TextView) view.findViewById(R.id.pages);
            //vh.pagesToGo (TextView) view.findViewById(R.id.)


            //vh.overskrift.setOnClickListener(vh);
            //vh.beskrivelse.setOnClickListener(vh);
            //vh.billede.setOnClickListener(vh);
            return vh;
        }

        @Override
        public void onBindViewHolder(ListeelemViewholder vh, int position) {
            vh.title.setText(lande.get(position));
            if (position>0) vh.title.append(" (flyt op)");

            vh.teacher.setText("Land nummer " + position + " på vh@"+Integer.toHexString(vh.hashCode()));
           /* if (position % 3 == 2) {
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

        public ListeelemViewholder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();
            final String landenavn = lande.get(position);
            Toast.makeText(v.getContext(), "Klik på " + position, Toast.LENGTH_SHORT).show();
            if (v == pages) { // Klik på billede fjerner landet fra listen
                lande.remove(position);
                adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, landenavn + " fjernet", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Fortryd", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lande.add(position, landenavn);
                                adapter.notifyItemInserted(position);
                                recyclerView.smoothScrollToPosition(position);
                                Snackbar.make(recyclerView, "OK, du får "+landenavn + " tilbage", Snackbar.LENGTH_LONG).show();
                            }
                        }).show();
            }

            if (v == title) { // Klik på overskrift flytter landet op til toppen
                lande.remove(position);
                lande.add(0, landenavn);
                adapter.notifyItemMoved(position, 0);
                recyclerView.scrollToPosition(0);
            }

            if (v == teacher) {
                Snackbar.make(recyclerView, "Prøv at skifte layout-manager", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Skift", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                skiftLayoutManager();
                            }
                        }).show();
            }
        }
    }


    /**
     * Skifter LayoutManager - kan fjernes
     */
    int aktivLayoutManager;
    private void skiftLayoutManager() {
        aktivLayoutManager++;
        String aktivLayoutManagerTekst;

        if (aktivLayoutManager==1) {
            recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            aktivLayoutManagerTekst = "GridLayoutManager 2 søjler";
        } else if (aktivLayoutManager == 2) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
            aktivLayoutManagerTekst = "StaggeredGridLayoutManager 2 søjler";
        } else if (aktivLayoutManager == 3) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
            aktivLayoutManagerTekst = "LinearLayoutManager vandret";
        } else if (aktivLayoutManager == 4) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            aktivLayoutManagerTekst = "LinearLayoutManager bagfra";
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            aktivLayoutManagerTekst = "LinearLayoutManager (normal)";
            aktivLayoutManager = 0;
        }
        Snackbar.make(recyclerView, aktivLayoutManagerTekst, Snackbar.LENGTH_INDEFINITE).setAction("Skift", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skiftLayoutManager();
            }
        }).show();
    }

}
