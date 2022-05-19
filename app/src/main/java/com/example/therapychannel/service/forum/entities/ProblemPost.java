package com.example.therapychannel.service.forum.entities;

import com.example.therapychannel.DAO.forum.DTO.ProblemPostDTO;

public class ProblemPost implements parsableDTO<ProblemPost, ProblemPostDTO> {
    String title;
    String content;

    public ProblemPost(){};

    public ProblemPost(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public ProblemPost parseDTO(ProblemPostDTO dto) {
        this.title = dto.title;
        this.content = dto.answer;
        return this;
    }
}
