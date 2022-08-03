package com.jungahzzzang.musicalcommunity.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.dto.MemberSessionDTO;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CustomMemberDetailsService implements UserDetailsService{

	private MemberRepository memberRepository;

	
	//name이 DB에 있는지 확인
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		Member principal = memberRepository.findByEmail(name).orElseThrow(()->
				new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.:"+name));
		
		//session.setAttribute("member", new MemberSessionDTO(member));
		
		//시큐리티 세션에 정보 저장
		return new CustomMemberDetails(principal);
	}
	
	
	
}
