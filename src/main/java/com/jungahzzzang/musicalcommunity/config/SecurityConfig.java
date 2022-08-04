package com.jungahzzzang.musicalcommunity.config;

import com.jungahzzzang.musicalcommunity.config.auth.CustomMemberDetailsService;
import com.jungahzzzang.musicalcommunity.config.auth.CustomOAuth2UserService;
import com.jungahzzzang.musicalcommunity.member.domain.Role;
import com.jungahzzzang.musicalcommunity.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

	@Autowired
    private CustomMemberDetailsService customMemberDetailsService;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	// TODO Auto-generated method stub
    	return super.authenticationManagerBean();
    }
    
    @Bean
    public BCryptPasswordEncoder encodePWD() {
    	return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customMemberDetailsService).passwordEncoder(encodePWD());
    }

    //http 관련 인증 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
		.csrf().disable()	//csrf 토큰 비활성화(테스트 시 걸어두는 게 좋음.)
		//요청이 들어올 때
		.authorizeRequests()
		//누구나 들어올 수 있다.
		.antMatchers("/","/oauth2/**", "/auth/**","/css/**","/js/**", "/image/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		//다른 경우에는 인증이 필요함.
		.and()
		.exceptionHandling()
		.and()
		.formLogin()
		.loginPage("/auth/loginForm")
		.loginProcessingUrl("/auth/loginProcess")	//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다.
		.defaultSuccessUrl("/")
		.and()
		.oauth2Login()
		.userInfoEndpoint()
		.userService(customOAuth2UserService)
		.and()
		.defaultSuccessUrl("/");
		/*
		 * .and() .logout().permitAll() .logoutUrl(null) .logoutSuccessUrl(null);
		 */

//            http.csrf().disable()
//					.authorizeRequests()
//					.antMatchers("/","/musical/**","/auth/**","/css/**","/js/**", "/image/**") .permitAll()
//					 
//					/*
//					 * .antMatchers("/member/login").permitAll()
//					 * .antMatchers("/member/**").hasRole(Role.USER) .anyRequest()
//					 */
//					.antMatchers("/member/login").permitAll()
//					.anyRequest()
//					.authenticated().and() 
//                    .formLogin()
//                    .loginPage("/auth/loginForm")
//                    .defaultSuccessUrl("/")
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/")
//                    .and()
//                    .oauth2Login()
//                    .userInfoEndpoint()
//                    .userService(customOAuth2UserService);
//    }
    }

    @Bean
    LoginFailHandler loginFailHandler() {
    	return new LoginFailHandler();
    }
}
