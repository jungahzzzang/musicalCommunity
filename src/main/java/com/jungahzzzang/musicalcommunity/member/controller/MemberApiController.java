package com.jungahzzzang.musicalcommunity.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.domain.Role;
import com.jungahzzzang.musicalcommunity.member.dto.MemberDTO;
import com.jungahzzzang.musicalcommunity.member.dto.ResponseDto;
import com.jungahzzzang.musicalcommunity.member.service.MemberService;

//@RestController
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping
public class MemberApiController {
	
	private final MemberService memberService;
    
    //회원가입 진행
    @PostMapping("/auth/joinProcess")
    public ResponseDto<Integer>  joinProcess(@RequestBody Member member) {
    	
    		//dto.setRole("MEMBER");
    	System.out.println("MemberController: save 호출됨");
    	//member.setRole(Role.USER);
        memberService.join(member);
    	
    	return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
