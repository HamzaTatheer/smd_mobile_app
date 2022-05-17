package com.example.therapychannel.service.forum;

import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;
import com.example.therapychannel.DAO.forum.DTO.ProblemPostDTO;

import java.util.ArrayList;

public interface IDAO {
    ArrayList<ProblemDTO> getProblems();
    ArrayList<ProblemPostDTO> getProblemPosts(String problem_name);
    void createProblemPost(String problem_name,String title,String answer);
}
