package com.simpleboard.board.controller;

import com.simpleboard.board.dto.BoardDto;
import com.simpleboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {

        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        return "board/list";
    }

    /**
     * 검색
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
        model.addAttribute("boardList", boardDtoList);
        return "board/list";
    }



    @GetMapping("/post")
    public String writeForm() {
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    //== 게시글 상세 ==//
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    //== 게시글 수정 ==//
    @GetMapping("/post/edit/{no}")
    public String updateForm(@PathVariable("no") Long no, Model model) {
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto", boardDto);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    //== 게시글 삭제 ==//
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

}
