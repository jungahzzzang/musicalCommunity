package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    //?€?λ§? ??λ¦¬ν°??? κΆν μ½λ? ?­? ROLE_?΄ ?? ??΄?Όλ§? ??€.
    GUEST("ROLE_GUEST","??"),
    MEMBER("ROLE_MEMBER","??");

    private final String key;
    private final String title;
}
