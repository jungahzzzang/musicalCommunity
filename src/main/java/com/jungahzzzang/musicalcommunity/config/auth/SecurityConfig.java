package com.jungahzzzang.musicalcommunity.config.auth;

import com.jungahzzzang.musicalcommunity.config.auth.CustomOAuth2UserService;
import com.jungahzzzang.musicalcommunity.member.domain.Role;
import com.jungahzzzang.musicalcommunity.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    
    //인증을 무시할 경로 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

    //http 관련 인증 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
					/*
					 * .authorizeRequests( authz->authz.antMatchers("/","/css/**","/js/**")
					 * .permitAll() .antMatchers("/member/login").permitAll()
					 * .antMatchers("/member/**").hasRole(Role.MEMBER.name()) .anyRequest()
					 * .authenticated().and() )
					 */
                    .formLogin()
                    .loginPage("/member/login")
                    .defaultSuccessUrl("/index.html",true)
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
