package com.app.library.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 */

public class ComFragmentAdapter extends FragmentPagerAdapter {
    List<String> titles;
    List<Fragment> fragments;

    public ComFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    public ComFragmentAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        this(fm, Arrays.asList(titles), Arrays.asList(fragments));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
