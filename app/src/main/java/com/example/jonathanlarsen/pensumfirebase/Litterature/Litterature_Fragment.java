package com.example.jonathanlarsen.pensumfirebase.Litterature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.DataObject.litterature;
import static com.example.jonathanlarsen.pensumfirebase.DataObject.pensumList;

public class Litterature_Fragment extends Fragment {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_litterature_, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.listview_litterature);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.setOnClickListener(this); //FINDES IKKE - i stedet skal man lytte efter onClick på de enkelte vieww
        recyclerView.setAdapter(adapter);


        return view;
    }



    RecyclerView.Adapter adapter = new RecyclerView.Adapter<Litterature_Fragment.ListeelemViewholder>() {



        @Override
        public int getItemCount() {
            return litterature.size();
        }

        @NonNull
        @Override
        public Litterature_Fragment.ListeelemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            Litterature_Fragment.ListeelemViewholder vh;


            View view = getLayoutInflater().inflate(R.layout.litteraturelist_item, parent, false);
            vh = new Litterature_Fragment.ListeelemViewholder(view);

            vh.title = (TextView) view.findViewById(R.id.title);
            vh.author = (TextView) view.findViewById(R.id.author);


            return vh;
            //          }

        }

        @Override
        public void onBindViewHolder(Litterature_Fragment.ListeelemViewholder vh, int position) {

            //pensumListView.get(pensumList.get( - Your pensumList - )).get( - Your Chosen listview - )

            if (position<10) {
                vh.author.setText("hej");

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
        TextView author;



        public ListeelemViewholder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            final int position = getAdapterPosition();
            final String pensum = pensumList.get(position);
            Toast.makeText(v.getContext(), "Klik på " + pensum, Toast.LENGTH_SHORT).show();

/*

            Litterature_Fragment nextFrag= new Litterature_Fragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MiddleContainer, nextFrag,"findThisFragment")
                    .addToBackStack(null)
                    .commit();
*/


        }

    }

}
