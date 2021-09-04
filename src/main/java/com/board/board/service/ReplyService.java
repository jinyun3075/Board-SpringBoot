package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.reply.Reply;
import com.board.board.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDto replyDto); //댓글 등록

    List<ReplyDto> getList(Long bno); // 특정 게시물 댓글 목록

    void modify(ReplyDto replyDto); // 댓글 수정

    void remove(Long rno); // 댓글 삭제

    // ReplyDto를 Reply객체로 변환 Board객체의 처리가 수반됨
    default Reply dtoToEntity(ReplyDto dto){
        Board board = Board.builder().bno(dto.getBno()).build();

        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
        return reply;
    }

    //Reply 객체를 ReplyDTO로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    default ReplyDto entityToDto(Reply reply){
        ReplyDto dto = ReplyDto.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }

}
