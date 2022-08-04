package com.jungahzzzang.musicalcommunity.config.auth;

import com.jungahzzzang.musicalcommunity.config.auth.dto.OAuthAttributes;
import com.jungahzzzang.musicalcommunity.config.auth.dto.SessionUser;
import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {

	@Autowired
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> userMap = new HashMap<>();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
        switch(registrationId) {
        case "kakao" :
        	Map<String, Object> profile = oAuth2User.getAttribute("kakao_account");
            Map<String, Object> temp = (Map<String, Object>) profile.get("profile");

            userMap.put("memberId", oAuth2User.getAttribute("id").toString());
            userMap.put("email", profile.get("email"));
            userMap.put("nickName", temp.get("nickname"));
            userMap.put("accessToken",userRequest.getAccessToken().getTokenValue());
        }
        
        userMap.put("loginType", registrationId);
        
        
        //PK?? 같�? ?���?
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);

        return oAuth2User;
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {

        Member member = memberRepository.findByUsername(attributes.getUsername())
                .orElse(attributes.toEntity());

        return memberRepository.save(member);
    }
}
