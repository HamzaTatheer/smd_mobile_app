package com.example.therapychannel.service.forum.entities;

public class Problem {
    private String name;
    private Integer postsCount;

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
}
