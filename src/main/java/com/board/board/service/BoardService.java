package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.member.Member;
import com.board.board.dto.BoardDto;
import com.board.board.dto.PageRequestDto;
import com.board.board.dto.PageResultDto;

public interface BoardService {
    Long register(BoardDto dto);

    PageResultDto<BoardDto,Object[]> getList(PageRequestDto pageRequestDto);

    BoardDto get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDto boardDto);

    default Board dtoToEntity(BoardDto dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDto entityToDto(Board board,Member member, Long replyCount){
        BoardDto boardDto = BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replayCount(replyCount.intValue())
                .build();
        return boardDto;
    }
}
