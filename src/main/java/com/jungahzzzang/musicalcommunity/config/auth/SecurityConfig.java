package com.jungahzzzang.musicalcommunity.config.auth;

import com.jungahzzzang.musicalcommunity.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .headers().frameOptions().disable()
                    .and()
//                    .authorizeRequests(
//                            authz->authz.antMatchers("/","/css/**","/js/**")
//                                    .permitAll()
//                                    .antMatchers("/musical/**").hasRole(Role.MEMBER.name())
//                                    .anyRequest()
//                                    .authenticated().and()
//                    )
                    .formLogin()
                    .loginPage("/login.html")
                    .defaultSuccessUrl("/index.html",true)
                    .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
