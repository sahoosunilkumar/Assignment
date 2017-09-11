package com.sixt.cars.app.home.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sixt.cars.R;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 8/25/17.
 */

class TabPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private Context context;
    private String[] titlesArr;

    public TabPagerAdapter(FragmentManager fragmentManager, Context context,
                           List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.context = context;
        titlesArr = this.context.getResources().getStringArray(R.array.title);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesArr[position];
    }
}