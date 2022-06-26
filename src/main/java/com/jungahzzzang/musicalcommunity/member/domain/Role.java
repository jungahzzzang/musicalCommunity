package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    //스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
    GUEST("ROLE_GUEST","손님"),
    MEMBER("ROLE_MEMBER","회원");

    private final String key;
    private final String title;
}
