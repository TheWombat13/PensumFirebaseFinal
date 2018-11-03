package com.example.jonathanlarsen.pensumfirebase.Litterature;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Adapter.Litterature_Adapter;
import com.example.jonathanlarsen.pensumfirebase.Element_ViewHolders.LitteratureListElementViewHolder;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;
import static com.example.jonathanlarsen.pensumfirebase.MainActivity.TAG;

public class Litterature_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Litterature_Adapter adapter;

    public static int pensumView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_litterature_, container, false);

        recyclerView = view.findViewById(R.id.listview_litterature);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new Litterature_Adapter();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //ToDO add onClick for litterature elements.

            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.d(TAG, "OnLongClick: " + view.getId());

                //ToDO doesn't work yet
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    LitteratureListElementViewHolder viewHolder =
                            (LitteratureListElementViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                    viewHolder.delete.setVisibility(View.GONE);
                }
                Toast.makeText(view.getContext(), "Hej", Toast.LENGTH_SHORT).show();
            }
        }));

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pensumView = bundle.getInt(PENSUM_BUNDLE_KEY, 0);
        }

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.litterature_divider));

        return view;
    }

}
