package com.example.therapychannel.service.forum.entities;

import android.os.Parcelable;

import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;

import java.io.Serializable;

public class Problem implements parsableDTO<Problem,ProblemDTO>, Serializable {
    private String name;
    private Integer postsCount;

    public Problem(){}

    public Problem(String name, Integer postsCount){
        this.name = name;
        this.postsCount = postsCount;
    }

    public String getName() {
        return name;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    @Override
    public Problem parseDTO(ProblemDTO dto) {
            setName(dto.name);
            setPostsCount(dto.postsCount);
            return this;
    }
}
