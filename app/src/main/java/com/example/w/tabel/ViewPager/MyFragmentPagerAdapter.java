package com.example.w.tabel.ViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public final int PAGE_COUNT = 2;
    FragmentManager fragmentManager;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager =  fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fr = null;

        if(position == 0){
            fr = new FirstFragment();
        }else{
            fr = new SecondFragment();
        }
        return fr;
    }

    @Override
    public int getCount() {
        return this.PAGE_COUNT;
    }
}
