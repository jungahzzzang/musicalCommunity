package com.jungahzzzang.musicalcommunity.member.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.dto.MemberDTO;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final MemberRepository memberRepository;
	
	private final BCryptPasswordEncoder encoder;

	@Transactional
	public Long join(MemberDTO dto) {
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		//비밀번호 암호화
		String hashPw = encoder.encode(dto.getPassword());
		
		Member member = dto.dtoToEntity();
		member.setPassword(hashPw);
		log.info("DB에 회원 저장 성공");
		
		return memberRepository.save(member).getMberId();
	}
	
	//name이 DB에 있는지 확인
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		//Optional<Member> memberEntityWrapper = memberRepository.findByEmail(name);
			
		Member member = memberRepository.findByEmail(name).orElseThrow(()->
				new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.:"+name));
			
		//session.setAttribute("member", new MemberSessionDTO(member));
			
		//시큐리티 세션에 정보 저장
		return new CustomUserDetails(member);
	}

}
