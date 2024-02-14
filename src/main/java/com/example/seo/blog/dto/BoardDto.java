package com.example.seo.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private int userId;
    private String category;
    private String title;
    private String content;
    private String writer;
    private String fileName;
    private String filePath;

    @Override
    public String toString() {
        return "BoardDto{" +
                "userId=" + userId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
