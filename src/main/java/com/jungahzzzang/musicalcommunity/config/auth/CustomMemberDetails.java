package com.jungahzzzang.musicalcommunity.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jungahzzzang.musicalcommunity.member.domain.Member;

import lombok.Data;

@Data
public class CustomMemberDetails implements UserDetails{
	
	@Autowired
	private Member member;
	
	public CustomMemberDetails(Member member) {
		
		this.member=member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		//collectors.add(new GrantedAuthority() {
			
			//@Override
			//public String getAuthority() {
				//스프링에서 ROLE을 받을 때 규칙임. ROLE_ 꼭 넣어줘야함.
				//return "ROLE_"+user.getRole();
			//}
		//});
		
		collectors.add(()->{return "ROLE_"+member.getRole();});
		
		return collectors;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
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
