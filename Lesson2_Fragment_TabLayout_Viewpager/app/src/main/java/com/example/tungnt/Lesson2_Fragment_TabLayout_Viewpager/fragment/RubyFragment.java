package com.example.tungnt.Lesson2_Fragment_TabLayout_Viewpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tungnt.Lesson2_Fragment_TabLayout_Viewpager.R;

public class RubyFragment extends Fragment {
    public RubyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ruby, container, false);
        return view;
    }

}
