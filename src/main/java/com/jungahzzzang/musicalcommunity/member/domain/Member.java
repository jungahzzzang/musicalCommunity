package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long mberId, String name, String email, String password, Role role){
        this.mberId = mberId;
        this.name = name;
        this.email = email;
        this.password=password;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
