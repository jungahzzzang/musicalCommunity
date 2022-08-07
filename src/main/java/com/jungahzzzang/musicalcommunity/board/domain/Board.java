package com.jungahzzzang.musicalcommunity.board.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jungahzzzang.musicalcommunity.member.domain.Member;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(excludes = {"board", "member"})
public class Board extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long postId;
	
	@Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

	/*
	 * @Column(length = 50, nullable = false) private String writer;
	 */
    
    @ManyToOne(fetch = FetchType.LAZY )
    private Member member;

    //제목과 내용을 수정할 수 있도록 메서드 추가
    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content=content;
    }
	

}
