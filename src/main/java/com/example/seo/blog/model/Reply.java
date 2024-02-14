package com.example.seo.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private int id;
    private int userId;
    private int boardId;
    private String content;
    private Timestamp regDate;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", userId=" + userId +
                ", boardId=" + boardId +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
