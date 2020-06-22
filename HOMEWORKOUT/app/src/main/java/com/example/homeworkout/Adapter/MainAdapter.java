package com.example.homeworkout.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> titleArraylist = new ArrayList<>();
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFr(Fragment fragment, String title){
    titleArraylist.add(title);
    fragmentArrayList.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleArraylist.get(position);
    }

}
