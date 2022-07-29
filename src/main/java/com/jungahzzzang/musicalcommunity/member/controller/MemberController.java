package com.jungahzzzang.musicalcommunity.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.dto.MemberDTO;
import com.jungahzzzang.musicalcommunity.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;

    @GetMapping("login")
    public String loginMember(){
        return "member/login";
    }
    
    //회원가입 폼
    @GetMapping("join")
    public String joinMember() {
    	return "member/join";
    }
    
    //회원가입 진행
    @PostMapping("joinProcess")
    public String joinProcess(MemberDTO dto) {
    	//dto.setRole("MEMBER");
    	memberService.join(dto);
    	
    	return "redirect:/login";
    }
}
