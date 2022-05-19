package com.example.therapychannel.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.therapychannel.DAO.forum.DAO;
import com.example.therapychannel.R;
import com.example.therapychannel.presentation.forum.MainHeader;
import com.example.therapychannel.presentation.forum.ProblemPostsList.ProblemPostsList;
import com.example.therapychannel.presentation.forum.ProblemsList.ProblemsList;
import com.example.therapychannel.service.forum.IDAO;
import com.example.therapychannel.service.forum.entities.Problem;

public class MainActivity extends AppCompatActivity {
    MainHeader headerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DAO.setType("web");
        Log.v("ttt","Hello");
        Log.e("TherapyChannel","Hello");
        Log.wtf("TherapyChannel","Hello");


        displayProblemList();

        headerFragment = MainHeader.newInstance(new MainHeader.Listener() {
            @Override
            public void onViewDiaryButtonClicked() {

            }

            @Override
            public void onBackButtonClicked() {
                displayProblemList();
                headerFragment.disableBackButton();
            }
        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_header,headerFragment,null)
                .commit();

    }

    void displayProblemList(){
        Fragment problemListFragment = ProblemsList.newInstance(new ProblemsList.Listener() {
            @Override
            public void onProblemSelect(Problem problem) {

                Log.v("TherapyChannel","fragment start");

                headerFragment.enableBackButton();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main_body, ProblemPostsList.newInstance(problem),null)
                        .commit();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_body,problemListFragment,null)
                .commit();
    }

}
