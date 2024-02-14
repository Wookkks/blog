package com.example.seo.blog.controller;

import com.example.seo.blog.common.Paging;
import com.example.seo.blog.model.Board;
import com.example.seo.blog.service.BoardService;
import com.example.seo.blog.vo.ReplyVo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "/board/write";
    }

    @GetMapping("/board")
    public String getBoardList(Model model, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "5") int size){
        List<Board> boards = boardService.findAll();
        int totalCount = boardService.totalCount();
        List<Board> boardList = boardService.getBoardList(currentPage, size);
        Paging<Board> paging = new Paging<>(totalCount, currentPage, 5, size, boards);
        model.addAttribute("boardList", boardList);
        model.addAttribute("paging", paging);
        return "/board/list";
    }
    @GetMapping("/board/{id}")
    public String boardDetail(Model model, @PathVariable int id, HttpServletRequest request, HttpServletResponse response){
        Board board = boardService.findById(id);
        String imgName = board.getFileName();
        List<ReplyVo> replyList = boardService.replyList(id);
        boolean isViewed = false;

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("board_viewed_"+ id)) {
                    isViewed = true;
                    break;
                }
            }
        }

        if(!isViewed) {
            boardService.viewCount(id);
            Cookie viewedCookie = new Cookie("board_viewed_" + id, "true");
            viewedCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(viewedCookie);
        }

        model.addAttribute("board", board);
        model.addAttribute("replyList", replyList);
        model.addAttribute("imgName", imgName);
        return "/board/detail";
    }
    @GetMapping("/board/update/{id}")
    public String boardUpdate(Model model, @PathVariable int id){
        Board board = boardService.findById(id);
        String imgName = board.getFileName();

        model.addAttribute("board", board);
        model.addAttribute("imgName", imgName);
        return "/board/update";
    }
}
