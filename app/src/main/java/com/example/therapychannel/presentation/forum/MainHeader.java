package com.example.therapychannel.presentation.forum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.therapychannel.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainHeader#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainHeader extends Fragment {

    public interface Listener{
        void onViewDiaryButtonClicked();
        void onBackButtonClicked();
    }

    Listener listener;
    ImageButton backButton;
    TextView headerTitle;
    Button diaryButton;


    public MainHeader(Listener listener) {
        this.listener = listener;
    }

    public static MainHeader newInstance(Listener listener) {
        MainHeader fragment = new MainHeader(listener);
        return fragment;
    }

    public void enableBackButton(){
        backButton.setVisibility(View.VISIBLE);
        headerTitle.setVisibility(View.GONE);
    }

    public void disableBackButton(){
        backButton.setVisibility(View.GONE);
        headerTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_header, container, false);
        backButton = (ImageButton) view.findViewById(R.id.header_back_button);
        headerTitle = (TextView)view.findViewById(R.id.header_title);
        diaryButton = (Button) view.findViewById(R.id.header_diary_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onBackButtonClicked();
            }
        });

        return view;
    }

}