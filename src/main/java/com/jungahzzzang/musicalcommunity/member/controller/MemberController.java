package com.jungahzzzang.musicalcommunity.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*
 * 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
 */
@Controller
public class MemberController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/auth/loginForm")
    public String loginMember(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "exception", required = false) String exception, Model model){
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		log.info("로그인");
		log.info("로그인 에러:"+exception);
		log.info("로그인 에러:"+error);
		
        return "member/login";
    }
    
    //회원가입 폼
    @GetMapping("/auth/joinForm")
    public String joinMember() {
    	return "member/join";
    }
    
}
