package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.Adapter.PensumList_Adapter;
import com.example.jonathanlarsen.pensumfirebase.ExpandebleMenu;
import com.example.jonathanlarsen.pensumfirebase.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Pensum_Fragment extends Fragment implements View.OnClickListener{

    public static Context context;
    public static RecyclerView recyclerView;
    private PensumList_Adapter adapter;
    private ExpandebleMenu expandebleMenu;

    private Toast toast = null;

    private Toolbar toolbar;
    private Button addPensum, editPensum, sendPensum;

    /*
    private ArcLayout arcLayout;
    private View expandMenu;
    private View menuLayout;
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pensum_, container, false);

        context = getContext();
        toolbar = getActivity().findViewById(R.id.toolbar);

        expandebleMenu = new ExpandebleMenu();

        recyclerView = view.findViewById(R.id.listview_pensum);

        addPensum = view.findViewById(R.id.add_pensum);
        sendPensum = view.findViewById(R.id.send_pensum);
        editPensum = view.findViewById(R.id.edit_pensum);
        expandebleMenu.expandMenu = view.findViewById(R.id.expand_menu);
        expandebleMenu.menuLayout = view.findViewById(R.id.menu_layout);
        expandebleMenu.arcLayout = view.findViewById(R.id.arc_layout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new PensumList_Adapter();

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL));

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.pensum_title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        startLayout();
        startOnClickListener();

        return view;
    }

    private void startLayout () {
        addPensum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        sendPensum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        editPensum.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));

    }

    private void startOnClickListener() {
        expandebleMenu.expandMenu.setOnClickListener(this);
        addPensum.setOnClickListener(this);
        sendPensum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.expand_menu) {
            onFabClick(v);
            return;
        }

        if (v.getId() == R.id.add_pensum) {
            AddPensum_Fragment nextFrag = new AddPensum_Fragment();

            AppCompatActivity activity = (AppCompatActivity) getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MiddleContainer, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }

        if (v.getId() == R.id.send_pensum) {
            SendPensum_Fragment frag = new SendPensum_Fragment();

            AppCompatActivity activity = (AppCompatActivity) getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.MiddleContainer, frag)
                    .addToBackStack(null)
                    .commit();
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

/*
    @SuppressWarnings("NewApi")
    private void showMenu() {
        menuLayout.setVisibility(View.VISIBLE);

        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
    }

    @SuppressWarnings("NewApi")
    private void hideMenu() {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                menuLayout.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();

    }

    private Animator createShowItemAnimator(View item) {

        float dx = expandMenu.getX() - item.getX();
        float dy = expandMenu.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 720f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

    private Animator createHideItemAnimator(final View item) {
        float dx = expandMenu.getX() - item.getX();
        float dy = expandMenu.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }
*/
}

