package com.example.therapychannel.service.forum;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.therapychannel.DAO.forum.DAO;
import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;
import com.example.therapychannel.service.forum.entities.Problem;

import java.io.IOException;
import java.util.ArrayList;

public class getProblemsList implements Runnable{

    public interface Listener{
        void onSuccess(ArrayList<Problem> response);
    }

    Listener listener;

    public getProblemsList(Listener listener){
        this.listener = listener;
    }


    @Override
    public void run() {
        //fetch data using DAO
        ArrayList<ProblemDTO> problemDTOS = null;
        problemDTOS = DAO.getDao().getProblems();



        ArrayList<Problem> dataset;
        dataset = new ArrayList<>();


        for(int i =0;i<problemDTOS.size();i++){
            dataset.add(new Problem().parseDTO(problemDTOS.get(i)));
        }


        //run on ui thread
        if(listener != null)
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(dataset);
            }
        });

    }


}







