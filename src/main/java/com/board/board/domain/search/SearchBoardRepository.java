package com.board.board.domain.search;

import com.board.board.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
