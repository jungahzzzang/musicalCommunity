package com.jungahzzzang.musicalcommunity.member.dto;

import java.io.Serializable;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.domain.Role;

import lombok.Getter;

@Getter
public class MemberSessionDTO implements Serializable{

	private String name;
	
	private String password;
	
	private String email;
	
	private Role role;
	
	public void sessionToDTO(Member member) {
		this.name=member.getName();
		this.password=member.getPassword();
		this.email=member.getEmail();
		this.role=member.getRole();
	}
}
