package ch.hsr.qfs.view;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsr.qfs.R;

public class QuizHomeFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot =  inflater.inflate(R.layout.quiz_tab_layout,null);

        ((MainActivity) getActivity()).hideFloatingActionButton(false);

        tabLayout = (TabLayout) viewRoot.findViewById(R.id.tabs);
        viewPager = (ViewPager) viewRoot.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return viewRoot;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new ClosedFragment();
                case 1 : return new QuizHomeOpenFragment();
                case 2 : return new ClosedFragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Laufend";
                case 1 :
                    return "Offen";
                case 2 :
                    return "Beendet";
            }
            return null;
        }
    }
}
