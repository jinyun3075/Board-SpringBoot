package com.board.board.service;

import com.board.board.dto.BoardDto;
import com.board.board.dto.PageRequestDto;
import com.board.board.dto.PageResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class BoardServiceTests {
    @Autowired BoardService boardService;

    @Test
    public void testRegister(){
        BoardDto dto = BoardDto.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com")
                .build();

        Long bno = boardService.register(dto);
    }
    @Test
    public void testList(){
        PageRequestDto pageRequestDto = new PageRequestDto();

        PageResultDto<BoardDto,Object[]> result = boardService.getList(pageRequestDto);

        for(BoardDto boardDto : result.getDtoList()){
            System.out.println(boardDto);
        }
    }
    @Test
    public void testGet(){
        Long bno = 100L;
        BoardDto boardDto = boardService.get(bno);
        System.out.println(boardDto);
    }

    @Test
    public void testRemove(){
        Long bno = 40L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){
        BoardDto boardDto = BoardDto.builder()
                .bno(2L)
                .title("제목 변경합니다.")
                .content("내용 변경합니다.")
                .build();
        boardService.modify(boardDto);
    }
}
