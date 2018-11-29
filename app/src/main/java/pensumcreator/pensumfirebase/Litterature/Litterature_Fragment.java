package pensumcreator.pensumfirebase.Litterature;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import pensumcreator.pensumfirebase.Adapter.Litterature_Adapter;
import pensumcreator.pensumfirebase.ExpandebleMenu;
import pensumcreator.pensumfirebase.Pensum.AddPensum_Fragment;
import pensumcreator.pensumfirebase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static pensumcreator.pensumfirebase.MainActivity.PENSUM_BUNDLE_KEY;
import static pensumcreator.pensumfirebase.MainActivity.TAG;
import static pensumcreator.pensumfirebase.MainActivity.item;

public class Litterature_Fragment extends Fragment implements View.OnClickListener{

    public static RecyclerView recyclerView;
    private Litterature_Adapter adapter;
    private Toolbar toolbar;
    private ExpandebleMenu expandebleMenu;

    private Toast toast = null;
    private String ToDo = "Not implented yet!";

    private ImageButton addLitterature, editLitterature, shareLitterature;
    private ImageView itemImage;

    public static int pensumView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_litterature_, container, false);

        expandebleMenu = new ExpandebleMenu();


        recyclerView = view.findViewById(R.id.listview_litterature);

        addLitterature = view.findViewById(R.id.add_litterature);
        editLitterature = view.findViewById(R.id.edit_litterature);
        shareLitterature = view.findViewById(R.id.share_litterature);
        expandebleMenu.expandMenu = view.findViewById(R.id.expand_menu);
        expandebleMenu.menuLayout = view.findViewById(R.id.menu_layout);
        expandebleMenu.arcLayout = view.findViewById(R.id.arc_layout);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new Litterature_Adapter();
        recyclerView.setAdapter(adapter);

        /*
         * Toolbar support
         */
        toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.litterature_title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


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
        /* recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this.getActivity(), R.drawable.litterature_divider));
*/
        startLayout();
        startOnClickListener();

        return view;
    }

    private void startLayout () {
        addLitterature.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        shareLitterature.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        editLitterature.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));

    }

    private void startOnClickListener() {
        expandebleMenu.expandMenu.setOnClickListener(this);
        addLitterature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.expand_menu) {
            onFabClick(v);
            return;
        }

        switch (v.getId()) {
            case R.id.add_litterature:
                AddLitterature_Fragment nextFrag = new AddLitterature_Fragment();

                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.MiddleContainer, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.edit_litterature:
                Toast.makeText(getActivity().getApplicationContext(), ToDo, Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_litterature:
                Toast.makeText(getActivity().getApplicationContext(), ToDo, Toast.LENGTH_SHORT).show();
                break;
                default:

        }

        if (v instanceof Button) {
            showToast((Button) v);
        }
    }

    private void showToast(Button btn) {
        if (toast != null) {
            toast.cancel();
        }

        String text = "Clicked: " + btn.getText();
        toast = Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

    }

    private void onFabClick(View v) {
        if (v.isSelected()) {
            expandebleMenu.hideMenu();
        } else {
            expandebleMenu.showMenu();
        }
        v.setSelected(!v.isSelected());
    }
}
