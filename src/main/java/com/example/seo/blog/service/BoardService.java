package com.example.seo.blog.service;

import com.example.seo.blog.dto.BoardDto;
import com.example.seo.blog.dto.ReplyDto;
import com.example.seo.blog.model.Board;
import com.example.seo.blog.vo.ReplyVo;

import java.util.List;

public interface BoardService {

    void write(BoardDto boardDto);
    List<Board> findAll();
    int totalCount();
    List<Board> getBoardList(int currentPage, int size);
    Board findById(int id);
    void updateBoard(Board board);
    void deleteBoard(int id);
    void deleteImg(int id);
    void viewCount(int id);
    void replyWrite(ReplyDto replyDto);
    List<ReplyVo> replyList(int id);
    void replyDelete(int id);
}
