package com.simpleboard.board.service;

import com.simpleboard.board.domain.BoardEntity;
import com.simpleboard.board.dto.BoardDto;
import com.simpleboard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;   //한 페이지에 존재하는 게시글 수

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();   //MVC계층간 Entity를 직접 운용하지 않고 DTO를 이용
    }

    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            BoardDto boardDto = convertEntityToDto(boardEntity);
            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public List<BoardDto> getBoardList(Integer pageNum) {

        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(
                pageNum - 1,
                PAGE_POST_COUNT,
                Sort.by(Sort.Direction.ASC, "createdDate")));

        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities)
            boardDtoList.add(convertEntityToDto(boardEntity));

        return boardDtoList;
    }

    public Long getBoardCount() {
        return boardRepository.count();
    }

    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();
        return convertEntityToDto(boardEntityWrapper.get());
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty())
            return boardDtoList;

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getTitle())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }

    public Integer[] getPageList(Integer curPageNum) {

        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        //총 게시글 갯수
        Double postsTotalCount = Double.valueOf(getBoardCount());

        //총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        //현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        //페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        //페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++)
            pageList[idx] = val;

        return pageList;
    }
}
