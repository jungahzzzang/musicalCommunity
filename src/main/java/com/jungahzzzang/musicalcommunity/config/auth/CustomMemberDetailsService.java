package com.jungahzzzang.musicalcommunity.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;

@Service
public class CustomMemberDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;

	
	//name이 DB에 있는지 확인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member principal = memberRepository.findByUsername(username)
							.orElseThrow(()->{
								return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: "+username);
							});
		
		//session.setAttribute("member", new MemberSessionDTO(member));
		
		//시큐리티 세션에 정보 저장
		return new CustomMemberDetails(principal);
	}
	
	
	
}
