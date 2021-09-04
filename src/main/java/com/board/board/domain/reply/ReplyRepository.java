package com.board.board.domain.reply;

import com.board.board.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    //게시판 삭제시 관련댓글 전부 삭제
    @Modifying
    @Query("delete from Reply r WHERE r.board.bno=:bno")
    void deleteByBno(@Param("bno") Long bno);

    //댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
