package com.example.therapychannel.presentation.forum;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.therapychannel.R;
import com.example.therapychannel.service.forum.MusicService.MusicPlayerService;

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
    Button insomniaButton;


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

    boolean mBound = false;
    MusicPlayerService playerService;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBound = true;
            playerService = ((MusicPlayerService.LocalBinder) iBinder).getService();
            playerService.playMusic(getContext());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_header, container, false);
        backButton = (ImageButton) view.findViewById(R.id.header_back_button);
        headerTitle = (TextView)view.findViewById(R.id.header_title);
        diaryButton = (Button) view.findViewById(R.id.header_diary_button);
        insomniaButton = (Button) view.findViewById(R.id.insomnia_music_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onBackButtonClicked();
            }
        });


        insomniaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, MusicPlayerService.class);
                context.bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
                context.startService(intent);



            }
        });

        return view;
    }

}