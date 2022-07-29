package com.jungahzzzang.musicalcommunity.member.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jungahzzzang.musicalcommunity.member.domain.Member;

public class CustomUserDetails implements UserDetails{
	
	private String email;
	
	private String password;
	
	private String auth;
	
	public CustomUserDetails(Member member) {
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.auth = "ROLE_"+member.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
