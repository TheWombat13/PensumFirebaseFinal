package pensumcreator.pensumfirebase.Litterature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import pensumcreator.pensumfirebase.Adapter.ViewPager_Adapter;
import pensumcreator.pensumfirebase.R;

public class ViewPager_Fragment extends Fragment {


    private ViewPager_Adapter viewPager_adapter;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);



        viewPager = view.findViewById(R.id.vp);
        viewPager_adapter = new ViewPager_Adapter(getChildFragmentManager());
        viewPager_adapter.addFrag(new AddLitterature_Fragment());
        viewPager_adapter.addFrag(new AddLitterature_Fragment2());


        viewPager.setAdapter(viewPager_adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        return view;
    }


}
