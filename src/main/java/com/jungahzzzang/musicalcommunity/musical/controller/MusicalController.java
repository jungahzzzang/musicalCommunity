package com.jungahzzzang.musicalcommunity.musical.controller;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import com.jungahzzzang.musicalcommunity.musical.dto.MusicalDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.musical.service.MusicalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/musical")
@Log4j2
@RequiredArgsConstructor
public class MusicalController {

    private final MusicalService musicalService;

    @GetMapping({"", "/"})
    public String index(Model model, @AuthenticationPrincipal Member principal) {
    	
    	if(principal == null) {
    		model.addAttribute("message", "Hello Spring Security");
    	}else {
    		model.addAttribute("message", "Hello Index" + principal.getName());
    	}
    	return "index";
    }
    
    @GetMapping("list")
    public void list(Model model, PageRequestDTO pageRequestDTO, Musical musical){

        model.addAttribute("result",musicalService.getList(pageRequestDTO));
    }
    
    @GetMapping("read")
    public void read(long mcode, @ModelAttribute("requestDTO")PageRequestDTO requestDTO, Model model) {
    	log.info("mcode:"+mcode);
    	
    	MusicalDTO musicalDTO = musicalService.getMusical(mcode);
    	model.addAttribute("dto",musicalDTO);
    }
}
