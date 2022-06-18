package com.simpleboard.board.repository;

import com.simpleboard.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // By = WHERE, Containing = LIKE -> %{keyword}%
    //- StartsWith = LIKE -> {keyword}%
    //- EndsWith = LIKE -> %{keyword}
    //- IgnoreCase
    //- Not
    List<BoardEntity> findByTitleContaining(String keyword);
}
