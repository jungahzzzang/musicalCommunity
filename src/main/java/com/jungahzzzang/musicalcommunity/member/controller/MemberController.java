package com.jungahzzzang.musicalcommunity.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/member/login")
    public String loginMember(){
        return "member/login";
    }
}
