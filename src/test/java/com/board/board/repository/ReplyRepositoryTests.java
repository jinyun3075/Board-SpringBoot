package com.board.board.repository;

import com.board.board.domain.board.Board;
import com.board.board.domain.reply.Reply;
import com.board.board.domain.reply.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository repository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1,300).forEach(i->{
            long bno = (long)(Math.random()*100)+1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply...."+i)
                    .board(board)
                    .replyer("guest")
                    .build();
            repository.save(reply);
        });
    }
    @Test
    public void readReply1(){
        Optional<Reply> reply = repository.findById(100l);
        Reply reply1 = reply.get();

        System.out.println(reply1);
        System.out.println(reply1.getBoard());
        System.out.println(reply1.getBoard().getWriter());
    }

    @Test
    public void testListByBoard(){
        List<Reply> replyList = repository.getRepliesByBoardOrderByRno(Board.builder()
                .bno(21L)
                .build());

        replyList.forEach(r->System.out.println(r));
    }

}
