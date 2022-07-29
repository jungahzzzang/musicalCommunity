package com.jungahzzzang.musicalcommunity.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.dto.MemberSessionDTO;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomMemberDetailsService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	private final HttpSession session;

	
	//name이 DB에 있는지 확인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.findByEmail(username).orElseThrow(()->
				new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.:"+username));
		
		//session.setAttribute("member", new MemberSessionDTO(member));
		
		//시큐리티 세션에 정보 저장
		return new CustomUserDetails(member);
	}
	
	
	
}
