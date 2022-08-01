package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    //?��?���? ?��?��리티?��?��?�� 권한 코드?�� ?��?�� ROLE_?�� ?��?�� ?��?��?���? ?��?��.
    GUEST("ROLE_ADMIN","?��?��"),
    MEMBER("ROLE_MEMBER","?��?��");

    private final String key;
    private final String title;
}
