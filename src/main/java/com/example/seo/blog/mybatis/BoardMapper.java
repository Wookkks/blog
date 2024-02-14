package com.example.seo.blog.mybatis;

import com.example.seo.blog.model.Board;
import com.example.seo.blog.model.Reply;
import com.example.seo.blog.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void write(Board board);
    void deleteBoard(int id);
    List<Board> findAll();
    int totalCount();
    List<Board> getBoardList(Map<String, Object> params);
    Board findById(int id);
    void updateBoard(Board board);
    void deleteImg(int id);
    void viewCount(int id);
    void replyWrite(Reply reply);
    List<ReplyVo> replyList(int id);
    void replyDelete(int id);
}
