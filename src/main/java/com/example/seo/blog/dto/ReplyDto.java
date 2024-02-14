package com.example.seo.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDto {

    private int userId;
    private int boardId;
    private String content;

    @Override
    public String toString() {
        return "ReplyDto{" +
                "userId=" + userId +
                ", boardId=" + boardId +
                ", content='" + content + '\'' +
                '}';
    }
}
