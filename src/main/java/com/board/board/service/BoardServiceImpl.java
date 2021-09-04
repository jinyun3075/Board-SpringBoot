package com.board.board.service;

import com.board.board.domain.board.Board;
import com.board.board.domain.board.BoardRepository;
import com.board.board.domain.member.Member;
import com.board.board.domain.reply.ReplyRepository;
import com.board.board.dto.BoardDto;
import com.board.board.dto.PageRequestDto;
import com.board.board.dto.PageResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
    private final BoardRepository repository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDto dto) {
        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
        log.info(pageRequestDto);
        Function<Object[],BoardDto> fn = (en->entityToDto((Board)en[0],(Member) en[1],(Long) en[2]));

        //Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDto.getPageable(Sort.by("bno").descending()));
        Page<Object[]> result =repository.searchPage(
          pageRequestDto.getType(),
          pageRequestDto.getKeyword(),
          pageRequestDto.getPageable(Sort.by("bno").descending())
        );
        return new PageResultDto<>(result,fn);
    }

    @Override
    public BoardDto get(Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr =(Object[])result;

        return entityToDto((Board)arr[0],(Member) arr[1],(Long) arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);
        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDto boardDto) {
        Board board = repository.getById(boardDto.getBno());

        board.changeTitle(boardDto.getTitle());
        board.changeContent(boardDto.getContent());

        repository.save(board);
    }
}
