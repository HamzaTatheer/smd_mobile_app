package com.example.therapychannel.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.therapychannel.R;
import com.example.therapychannel.presentation.forum.ProblemPostsList.ProblemPostsList;
import com.example.therapychannel.presentation.forum.ProblemsList.ProblemsList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        Fragment problemListFragment = ProblemsList.newInstance(new ProblemsList.Listener() {
            @Override
            public void onProblemSelect(String problem_name) {

                Log.v("TherapyChannel","fragment start");


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main_body, ProblemPostsList.newInstance(problem_name),null)
                        .commit();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_body,problemListFragment,null)
                .commit();
    }

}
