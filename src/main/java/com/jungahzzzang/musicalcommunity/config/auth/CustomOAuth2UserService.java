package com.jungahzzzang.musicalcommunity.config.auth;

import com.jungahzzzang.musicalcommunity.config.auth.dto.OAuthAttributes;
import com.jungahzzzang.musicalcommunity.config.auth.dto.SessionUser;
import com.jungahzzzang.musicalcommunity.member.domain.Member;
import com.jungahzzzang.musicalcommunity.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //?òÑ?û¨ Î°úÍ∑∏?ù∏ ÏßÑÌñâ WÏ§ëÏù∏ ?ÑúÎπÑÏä§Î•? Íµ¨Î∂Ñ?ïò?äî ÏΩîÎìú(Ï∂îÌõÑ ?Ñ§?ù¥Î≤? Î°úÍ∑∏?ù∏ Ï∂îÍ? ?ãú ?Ñ§?ù¥Î≤ÑÏù∏Ïß? Íµ¨Í??ù∏Ïß? Íµ¨Î∂Ñ?ïòÍ∏? ?úÑ?ï¥ ?Ç¨?ö©)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //PK?? Í∞ôÏ? ?ùòÎØ?
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);

        httpSession.setAttribute("member", new SessionUser(member));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {

        Member member = memberRepository.findByEmail(attributes.getEmail())
                .orElse(attributes.toEntity());

        return memberRepository.save(member);
    }
}
