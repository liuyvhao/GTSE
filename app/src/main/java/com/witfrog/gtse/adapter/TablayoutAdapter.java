package com.witfrog.gtse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TablayoutAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titles;
    private Fragment[] mFragments;

    public TablayoutAdapter(FragmentManager fm,ArrayList<String> titles,Fragment[] mFragments) {
        super(fm);
        this.titles=titles;
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}
