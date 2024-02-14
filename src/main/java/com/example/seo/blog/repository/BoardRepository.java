package com.example.seo.blog.repository;

import com.example.seo.blog.model.Board;
import com.example.seo.blog.model.Reply;
import com.example.seo.blog.mybatis.BoardMapper;
import com.example.seo.blog.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class BoardRepository implements BoardMapper {

    private final BoardMapper boardMapper;
    @Override
    public void write(Board board) {
        boardMapper.write(board);
    }
    @Override
    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }
    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }
    @Override
    public int totalCount() {
        return boardMapper.totalCount();
    }
    @Override
    public List<Board> getBoardList(Map<String, Object> params) {
        return boardMapper.getBoardList(params);
    }
    @Override
    public Board findById(int id) {
        return boardMapper.findById(id);
    }
    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }
    @Override
    public void deleteImg(int id) {
        boardMapper.deleteImg(id);
    }

    @Override
    public void viewCount(int id) {
        boardMapper.viewCount(id);
    }
    @Override
    public void replyWrite(Reply reply) {
        boardMapper.replyWrite(reply);
    }
    @Override
    public List<ReplyVo> replyList(int id) {
        return boardMapper.replyList(id);
    }

    @Override
    public void replyDelete(int id) {
        boardMapper.replyDelete(id);
    }
}
