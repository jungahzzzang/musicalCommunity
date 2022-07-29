package com.jungahzzzang.musicalcommunity.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jungahzzzang.musicalcommunity.member.dto.MemberDTO;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public Long join(MemberDTO dto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		return memberRepository.save(dto.dtoToEntity()).getMberId();
	}

}
