package com.example.seo.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int id;
    private int userId;
    private String category;
    private String title;
    private String content;
    private String fileName;
    private String filePath;
    private String writer;
    private int count;
    private Timestamp regDate;
    private Timestamp updateDate;

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", count=" + count +
                ", regDate=" + regDate +
                '}';
    }
}
