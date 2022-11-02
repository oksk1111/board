package com.simpleboard.board.api;

import com.simpleboard.board.domain.BoardEntity;
import com.simpleboard.board.dto.BoardDto;
import com.simpleboard.board.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardApiController {

    private final BoardService boardService;

    /**
     * 게시글 목록
     * @param
     * @return
     */
//    @GetMapping("/board/all")
//    public List<BoardDto> getBoardList() {
//        return boardService.getBoardList();
//    }

    @GetMapping("/board")
    public ResponseEntity<Map> getBoardList(@RequestParam(value="p_num", required=false, defaultValue="1") Integer pageNum) {
        return boardService.getBoardList(pageNum);
    }


    @PostMapping("/post")
    public CreateBoardResponse write(@RequestBody @Valid BoardDto boardDto) {
        Long id = boardService.savePost(boardDto);
        return new CreateBoardResponse(id);
    }


    @GetMapping("/board/{id}")
    public BoardDto detail(@PathVariable("id") Long id) {
        return boardService.getPost(id);
    }


    @PutMapping("/post/edit/{id}")
    public CreateBoardResponse update(@PathVariable("id") Long id, @RequestBody @Valid BoardDto boardDto) {
        boardService.savePost(boardDto);
        return new CreateBoardResponse(id);
    }


    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
    }



    @Data
    static class CreateBoardResponse {
        private Long id;

        public CreateBoardResponse(Long id) {
            this.id = id;
        }
    }

}
