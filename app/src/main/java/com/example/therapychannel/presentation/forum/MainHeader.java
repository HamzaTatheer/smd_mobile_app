package com.example.therapychannel.presentation.forum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.therapychannel.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainHeader#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainHeader extends Fragment {

    interface Listener{
        void onViewDiaryButtonClicked();
        void onBackButtonClicked();
    }

    public void setBackButtonVisible(Boolean isVisible){
    }

    public MainHeader() {
        // Required empty public constructor
    }

    public static MainHeader newInstance() {
        MainHeader fragment = new MainHeader();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_header, container, false);

        return view;
    }
}