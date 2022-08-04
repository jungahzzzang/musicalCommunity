package com.jungahzzzang.musicalcommunity.config.auth.dto;

import com.jungahzzzang.musicalcommunity.member.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String username;
    private String email;

    public SessionUser(Member member){
        this.username=member.getUsername();
        this.email=member.getEmail();
    }
}
