package com.example.tungnt.androidadvance1_lesson2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tungnt.androidadvance1_lesson2.fragment.AndroidFragment;
import com.example.tungnt.androidadvance1_lesson2.fragment.PHPFragment;
import com.example.tungnt.androidadvance1_lesson2.fragment.RubyFragment;

/**
 * Created by nguyen on 11/05/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    public ViewPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new AndroidFragment();
                break;
            case 1:
                fragment = new RubyFragment();
                break;
            case 2:
                fragment = new PHPFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Android";
                break;
            case 1:
                title = "Ruby";
                break;
            case 2:
                title = "PHP";
                break;
        }
        return title;
    }
}
