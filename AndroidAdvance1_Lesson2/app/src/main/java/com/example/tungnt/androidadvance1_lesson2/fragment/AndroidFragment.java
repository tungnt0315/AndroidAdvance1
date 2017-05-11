package com.example.tungnt.androidadvance1_lesson2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tungnt.androidadvance1_lesson2.R;

public class AndroidFragment extends Fragment {
    public AndroidFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_android, container, false);
        return view;
    }

}
