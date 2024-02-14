package com.example.seo.blog.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVo {
    private int replyId;
    private String content;
    private int userId;
    private int boardId;
    private String name;
}
