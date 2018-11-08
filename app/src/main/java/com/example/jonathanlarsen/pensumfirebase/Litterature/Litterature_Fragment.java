package com.example.jonathanlarsen.pensumfirebase.Litterature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonathanlarsen.pensumfirebase.Adapter.Litterature_Adapter;
import com.example.jonathanlarsen.pensumfirebase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;

public class Litterature_Fragment extends Fragment {

    public static RecyclerView recyclerView;
    private Litterature_Adapter adapter;
    private Toolbar toolbar;
    private FloatingActionButton addLitteratureBtn;

    public static int pensumView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_litterature_, container, false);


        recyclerView = view.findViewById(R.id.listview_litterature);
        addLitteratureBtn = view.findViewById(R.id.addLitteratureBtn);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new Litterature_Adapter();
        recyclerView.setAdapter(adapter);

        /*
         * Toolbar support
         */
        /*toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();*/

        /*
         * This method is apperently the correct way to do it, but it comes with a varity of issues
         * which is not easily fixable and should be used with caution.
         *
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                /*
                 * add onClick for litterature elements.
                 *

            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.d(TAG, "OnLongClick: " + view.getId());

                /*
                 * viewHolder.delete Checkbox is not responding correctly, its slow, require multiple clicks etc.
                 *
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    LitteratureListElementViewHolder viewHolder =
                            (LitteratureListElementViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                    viewHolder.delete.setVisibility(View.VISIBLE);
                }
                Toast.makeText(view.getContext(), "Hej", Toast.LENGTH_SHORT).show();
            }
        }));*/

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pensumView = bundle.getInt(PENSUM_BUNDLE_KEY, 0);
        }


        //Setting lines between cells
        /*

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.litterature_divider));

*/
        addLitteratureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLitterature_Fragment nextFrag = new AddLitterature_Fragment();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.MiddleContainer, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
