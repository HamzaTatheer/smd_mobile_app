package com.example.therapychannel.service.forum;

import android.os.Handler;
import android.os.Looper;

import com.example.therapychannel.DAO.forum.DAO;
import com.example.therapychannel.DAO.forum.DTO.ProblemPostDTO;
import com.example.therapychannel.service.forum.entities.Problem;
import com.example.therapychannel.service.forum.entities.ProblemPost;

import java.util.ArrayList;

public class getProblemPosts implements Runnable{

    public interface Listener{
        void onSuccess(ArrayList<ProblemPost> response);
    };

    Listener listener;


    Problem problem;

    public getProblemPosts(Problem problem, Listener listener){
        this.problem = problem;
        this.listener = listener;
    }

    @Override
    public void run() {
        //fetch thru api

        ArrayList<ProblemPostDTO> problemPostDTOS = DAO.getDao().getProblemPosts(this.problem.getName());
        ArrayList<ProblemPost> posts = new ArrayList<>();

        for(int i =0;i<problemPostDTOS.size();i++){
            posts.add(new ProblemPost().parseDTO(problemPostDTOS.get(i)));
        }

        //run on ui thread
        if(listener != null)
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    listener.onSuccess(posts);
                }
            });

    }
}



