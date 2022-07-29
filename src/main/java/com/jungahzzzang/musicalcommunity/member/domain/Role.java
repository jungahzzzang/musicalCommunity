package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    //?Š¤?”„ë§? ?‹œ?ë¦¬í‹°?—?„œ?Š” ê¶Œí•œ ì½”ë“œ?— ?•­?ƒ ROLE_?´ ?•?— ?ˆ?–´?•¼ë§? ?•œ?‹¤.
    GUEST("ROLE_GUEST","?†?‹˜"),
    MEMBER("ROLE_MEMBER","?šŒ?›");

    private final String key;
    private final String title;
}
