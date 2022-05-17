package com.example.therapychannel.DAO.forum;

import android.util.Log;

import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;
import com.example.therapychannel.DAO.forum.DTO.ProblemPostDTO;
import com.example.therapychannel.service.forum.IDAO;

import java.util.ArrayList;

public class MockDao implements IDAO {
    @Override
    public ArrayList<ProblemDTO> getProblems() {
        ArrayList<ProblemDTO> p = new ArrayList<>();
        p.add(new ProblemDTO("Weight loss",2));
        p.add(new ProblemDTO("anxiety",2));
        p.add(new ProblemDTO("Gym motivation",2));
        return p;
    }

    @Override
    public ArrayList<ProblemPostDTO> getProblemPosts(String problem_name) {
        ArrayList<ProblemPostDTO> p = new ArrayList<>();
        p.add(new ProblemPostDTO("How to lose weight","Exercise More"));
        p.add(new ProblemPostDTO("Food that reduced weight","Exercise More"));
        return p;
    }

    @Override
    public void createProblemPost(String title, String question, String answer) {
        Log.v("TherapyChannel","Post of " + title + "created");
    }
}
