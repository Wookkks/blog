package com.example.seo.blog.controller.api;

import com.example.seo.blog.common.FileRegist;
import com.example.seo.blog.dto.BoardDto;
import com.example.seo.blog.dto.ReplyDto;
import com.example.seo.blog.dto.ResponseDto;
import com.example.seo.blog.model.Board;
import com.example.seo.blog.service.BoardService;
import com.example.seo.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;
    private final UserService userService;

    @PostMapping("/board/api/write")
    public ResponseDto write(@RequestPart(value = "boardDTO") BoardDto boardDTO
            , @RequestPart(value = "file", required = false) MultipartFile file) {
        try{
            if(file != null && !file.isEmpty()) {
                FileRegist register = new FileRegist(file);
                boardDTO.setFileName(register.getFileName());
                boardDTO.setFilePath(register.getFilePath());
            }

            String writer = userService.findById(boardDTO.getUserId()).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다.")).getName();
            boardDTO.setWriter(writer);
            boardService.write(boardDTO);
            return new ResponseDto("success", "글쓰기가 완료되었습니다.");

        }catch (IOException e){
            e.printStackTrace();
            return new ResponseDto("failure", "알 수 없는 에러가 발생하였습니다.");
        }
    }
    @DeleteMapping("/board/api/{id}")
    public ResponseDto deleteById(@PathVariable int id) {
        boardService.deleteBoard(id);
        return new ResponseDto("success", "삭제가 완료되었습니다.");
    }
    @PostMapping("/board/api/deleteImg/{id}")
    public ResponseDto deleteImgById(@PathVariable int id){
        boardService.deleteImg(id);
        return new ResponseDto("success", "이미지가 삭제되었습니다.");
    }
    @PostMapping("/board/api/replyWrite")
    public ResponseDto replyWrite(@RequestBody ReplyDto replyDto){
        boardService.replyWrite(replyDto);
        return new ResponseDto("success", "댓글이 등록되었습니다.");
    }
    @DeleteMapping("/board/api/replyDelete/{id}")
    public ResponseDto replyDelete(@PathVariable int id){
        boardService.replyDelete(id);
        return new ResponseDto("success", "댓글이 삭제되었습니다.");
    }

    @PutMapping("/board/api/{id}")
    public ResponseDto update(
            @PathVariable int id,
            @RequestPart(value = "boardDTO") BoardDto boardDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Board board = boardService.findById(id);
        log.info(boardDTO.toString());
        if(file != null && !file.isEmpty()) {
            FileRegist register = new FileRegist(file);
            board.setFilePath(register.getFilePath());
            board.setFileName(register.getFileName());
        }
        board.setCategory(boardDTO.getCategory());
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        log.info(board.toString());
        boardService.updateBoard(board);
        return new ResponseDto("success", "글 수정이 완료되었습니다.");
    }
}
