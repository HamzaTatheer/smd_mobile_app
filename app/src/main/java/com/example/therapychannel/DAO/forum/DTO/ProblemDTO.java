package com.example.therapychannel.DAO.forum.DTO;

public class ProblemDTO {
    public String name;
    public Integer postsCount;

    public ProblemDTO(String name, Integer postsCount){
        this.name = name;
        this.postsCount = postsCount;
    }

    public ProblemDTO() {

    }
}
