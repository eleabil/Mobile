/*
package com.example.firebaseauth.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firebaseauth.R;
import com.example.firebaseauth.TabsAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TabsFragment extends Fragment {

    TabsAdapter tabsAdapter;
    ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final ViewPager2 viewPager = view.findViewById(R.id.fragment_tabs_view_pager);
        final TabsAdapter tabsAdapter = new TabsAdapter(this);
        viewPager.setAdapter(tabsAdapter);
    }

    private void setupTabs(final View view) {
        final TabLayout tabLayout = view.findViewById(R.id.fragment_tabs_tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("OBJECT " + (position + 1));
                    }
                }
        ).attach();

    }

*/
/*    private static class TabsAdapter extends FragmentStateAdapter{
        private static final List<Fragment> FRAGMENTS = new ArrayList<Fragment>() {
            {
                add(new DataListFragment());
                add(new SampleFragment());
                add(new ProfileFragment());
            }
        };

        private TabsAdapter(final Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(final int position) {
            return FRAGMENTS.get(position);
        }

        @Override
        public int getItemCount() {
            return FRAGMENTS.size();
        }

    }*//*

}
*/
