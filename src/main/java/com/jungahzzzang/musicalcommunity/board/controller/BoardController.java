package com.jungahzzzang.musicalcommunity.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jungahzzzang.musicalcommunity.board.dto.BoardDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	
	private final BoardService boardService;
	
	@GetMapping("/postList")
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list............."+pageRequestDTO);
		
		model.addAttribute("result", boardService.getBoardList(pageRequestDTO));
		
		return "/board/list";
	}
	
	@GetMapping("/registerForm")
	public String register() {
		log.info("register get...");
		return "board/register";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Long> addReview(@RequestBody BoardDTO boardDTO) {
		log.info("--------------------add POST------------------");
		log.info("boardDTO:"+boardDTO);
		Long postId = boardService.register(boardDTO);
		
		return new ResponseEntity<>(postId, HttpStatus.OK);
		
		//새로 추가된 글번호
		//Long postId = boardService.register(dto);
		//redirectAttributes.addFlashAttribute("msg", postId);
		//return "redirect:board/postList";
	}
	
	//글 조회
	@GetMapping({"/read", "/modify"})
	public void read(long postId, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("postId:"+postId);
		
		BoardDTO dto = boardService.read(postId);
		model.addAttribute("dto",dto);
	}
	
	//글 삭제
    @PostMapping("/remove")
    public String remove(long postId, RedirectAttributes redirectAttributes){
        log.info("postId: "+postId);

        boardService.remove(postId);
        redirectAttributes.addFlashAttribute("msg",postId);
        return "redirect:/board/postList";
    }

    //글 수정
    @PostMapping("/modify")
    public String modify(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, BoardDTO dto, RedirectAttributes redirectAttributes){
        log.info("post modify--------------------------------------");
        log.info("dto: "+dto);

        boardService.modify(dto);
        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword",requestDTO.getKeyword());
        redirectAttributes.addAttribute("postId",dto.getPostId());
        return "redirect:/board/read";
    }
	
}
