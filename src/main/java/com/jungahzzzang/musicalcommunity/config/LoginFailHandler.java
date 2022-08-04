package com.jungahzzzang.musicalcommunity.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

	/*
	 * httpServletRequest -> request에 대한 정보, httpServletResponse -> response에 대해 설정 가능한 변수
	 * AuthenticationException e -> 로그인 실패 시 예외에 대한 정보를 담고 있음.
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("login fail handler");
		
		String errorMessage;
		
		if(exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 아이디입니다.";
		}else {
			errorMessage = "알 수 없는 이유로 로그인이 안 되고 있습니다.";
		}
		
		errorMessage = URLEncoder.encode(errorMessage,"UTF-8");	//한글 인코딩 깨지는 문제 방지
		setDefaultFailureUrl("/auth/loginForm?error=true&exception="+errorMessage);
		super.onAuthenticationFailure(request, response, exception);
	}
}
