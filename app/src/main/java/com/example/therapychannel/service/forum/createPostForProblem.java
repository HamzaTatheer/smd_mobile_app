package com.example.therapychannel.service.forum;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.therapychannel.DAO.forum.DAO;
import com.example.therapychannel.service.forum.entities.Problem;
import com.example.therapychannel.service.forum.entities.ProblemPost;

import java.util.ArrayList;

public class createPostForProblem implements Runnable{

    Problem problem;
    ProblemPost post;
    public interface Listener{
        void onSuccess();
    };

    Listener listener;


    public createPostForProblem(Problem problem,ProblemPost post,Listener listener){
        this.problem = problem;
        this.post = post;
        this.listener = listener;
    }

    @Override
    public void run() {
        //send api call
        DAO.getDao().createProblemPost(problem.getName(),post.getTitle(),post.getContent());

        if(listener == null)
            return;


        //run on ui thread
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess();
            }
        });




    }



}
