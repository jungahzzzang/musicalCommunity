package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long mberId, String name, String email, Role role){
        this.mberId = mberId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
