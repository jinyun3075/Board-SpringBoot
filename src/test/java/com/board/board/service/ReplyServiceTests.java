package com.board.board.service;

import com.board.board.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList(){
        Long bno = 17L;
        List<ReplyDto> replyDtoList = replyService.getList(bno);

        replyDtoList.forEach(i->System.out.println(i));
    }
}
