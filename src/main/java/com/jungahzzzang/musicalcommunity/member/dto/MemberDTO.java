package com.jungahzzzang.musicalcommunity.member.dto;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Role role;
	
	public Member dtoToEntity() {
		Member member = Member.builder()
				.name(name)
				.email(email)
				.password(password)
				.role(role)
				.build();
		return member;
	}

}
