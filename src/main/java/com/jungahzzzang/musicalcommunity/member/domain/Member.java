package com.jungahzzzang.musicalcommunity.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Getter
@NoArgsConstructor
@Builder
@Entity
@Setter
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mberId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long mberId, String username, String email, String password, Role role){
        this.mberId = mberId;
        this.username = username;
        this.email = email;
        this.password=password;
        this.role = role;
    }
    
   
}

	/*
	 * public String getRoleKey(){ return this.role.getKey(); }
	 */

