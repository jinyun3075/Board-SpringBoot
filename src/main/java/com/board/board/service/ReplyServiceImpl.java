package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.reply.Reply;
import com.board.board.domain.reply.ReplyRepository;
import com.board.board.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDto replyDto) {
        Reply reply = dtoToEntity(replyDto);

        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDto> getList(Long bno) {

        List<Reply> result =replyRepository
                .getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());

        return result.stream().map(r->entityToDto(r)).collect(Collectors.toList());
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public void modify(ReplyDto replyDto) {
        Reply reply = dtoToEntity(replyDto);
        replyRepository.save(reply);
    }

    @Override
    public Reply dtoToEntity(ReplyDto dto) {
        return ReplyService.super.dtoToEntity(dto);
    }

    @Override
    public ReplyDto entityToDto(Reply reply) {
        return ReplyService.super.entityToDto(reply);
    }
}
