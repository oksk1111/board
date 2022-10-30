package com.simpleboard.board.api;

import com.simpleboard.board.domain.BoardEntity;
import com.simpleboard.board.dto.BoardDto;
import com.simpleboard.board.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /**
     * 게시글 목록
     * @param
     * @return
     */
    @GetMapping("/board")
    public List<BoardDto> getBoardList() {
        return boardService.getBoardList();
    }


    @PostMapping("/post")
    public CreateBoardResponse write(@RequestBody @Valid BoardDto boardDto) {
        Long id = boardService.savePost(boardDto);
        return new CreateBoardResponse(id);
    }

    @Data
    static class CreateBoardResponse {
        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }
    }
}
