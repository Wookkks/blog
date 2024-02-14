package com.example.seo.blog.service;

import com.example.seo.blog.dto.BoardDto;
import com.example.seo.blog.dto.ReplyDto;
import com.example.seo.blog.model.Board;
import com.example.seo.blog.model.Reply;
import com.example.seo.blog.repository.BoardRepository;
import com.example.seo.blog.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    @Override
    public void write(BoardDto boardDto) {
        Board board = new Board();
        board.setCategory(boardDto.getCategory());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setFileName(boardDto.getFileName());
        board.setFilePath(boardDto.getFilePath());
        board.setWriter(boardDto.getWriter());
        board.setUserId(boardDto.getUserId());
        boardRepository.write(board);
    }
    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
    @Override
    public int totalCount() {
        return boardRepository.totalCount();
    }

    @Override
    public List<Board> getBoardList(int currentPage, int size) {
        int startRow = (currentPage - 1) * size;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("size", size);
        return boardRepository.getBoardList(params);
    }
    @Override
    public Board findById(int id) {
        return boardRepository.findById(id);
    }
    @Override
    public void updateBoard(Board board) {
        boardRepository.updateBoard(board);
    }
    @Override
    public void deleteBoard(int id) {
        boardRepository.deleteBoard(id);
    }
    @Override
    public void deleteImg(int id) {
        boardRepository.deleteImg(id);
    }
    @Override
    public void viewCount(int id) {
        boardRepository.viewCount(id);
    }
    @Override
    public void replyWrite(ReplyDto replyDto) {
        Reply reply = new Reply();
        reply.setBoardId(replyDto.getBoardId());
        reply.setUserId(replyDto.getUserId());
        reply.setContent(replyDto.getContent());
        boardRepository.replyWrite(reply);
    }
    @Override
    public List<ReplyVo> replyList(int id) {
        return boardRepository.replyList(id);
    }

    @Override
    public void replyDelete(int id) {
        boardRepository.replyDelete(id);
    }
}
