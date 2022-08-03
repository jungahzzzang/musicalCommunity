package com.jungahzzzang.musicalcommunity.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
 */
@Controller
public class MemberController {

	@GetMapping("/auth/loginForm")
    public String loginMember(){
        return "member/login";
    }
    
    //회원가입 폼
    @GetMapping("/auth/joinForm")
    public String joinMember() {
    	return "member/join";
    }
}
