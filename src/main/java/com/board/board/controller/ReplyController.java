package com.board.board.controller;

import com.board.board.dto.ReplyDto;
import com.board.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 메서드의 리턴타입을 기본으로 JSON 사용
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody ReplyDto replyDto){
        log.info(replyDto+"저장");
        Long rno = replyService.register(replyDto);
        return new ResponseEntity<>(rno,HttpStatus.OK);
    }

    @GetMapping(value = "/board/{bno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getListByBoard(@PathVariable("bno") Long bno){
        log.info("bno : "+bno);

        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("댓글삭제 RNO:"+rno);
        replyService.remove(rno);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDto replyDto){
        log.info("수정 :"+replyDto);

        replyService.modify(replyDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
