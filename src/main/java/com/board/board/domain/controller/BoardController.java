package com.board.board.domain.controller;

import com.board.board.dto.BoardDto;
import com.board.board.dto.PageRequestDto;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model){
        log.info("list................."+pageRequestDto);
        model.addAttribute("result",boardService.getList(pageRequestDto));
    }
    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDto dto, RedirectAttributes redirectAttributes){
        log.info("dto....."+dto);

        Long bno = boardService.register(dto);

        log.info("BNO:"+bno);
        redirectAttributes.addFlashAttribute("msg",bno);

        return "redirect:/board/list";
    }
    @GetMapping({"/read","/modify"})
    public void read(PageRequestDto pageRequestDto,Long bno,Model model){
        log.info("bno: "+bno);
        BoardDto boardDto = boardService.get(bno);
        log.info(boardDto);
        model.addAttribute("dto",boardDto);
    }
    @PostMapping("/remove")
    public String remove(long bno,RedirectAttributes redirectAttributes){
        log.info("bno:" +bno);
        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }
    @PostMapping("/modify")
    public String modify(BoardDto dto, PageRequestDto pageRequestDto, RedirectAttributes redirectAttributes){
        log.info("post modify..................");
        log.info("dto:"+dto);
        boardService.modify(dto);
        redirectAttributes.addAttribute("page",pageRequestDto.getPage());
        redirectAttributes.addAttribute("type",pageRequestDto.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDto.getKeyword());
        redirectAttributes.addAttribute("bno",dto.getBno());

        return "redirect:/board/read";
    }
}
