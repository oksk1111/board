package com.simpleboard.board.service;

import com.simpleboard.board.dto.BoardDto;
import com.simpleboard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();   //MVC계층간 Entity를 직접 운용하지 않고 DTO를 이용
    }
}
