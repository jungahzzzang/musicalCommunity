package com.jungahzzzang.musicalcommunity.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass	//해당 어노테이션이 적용된 클래슨느 테이블로 생성되지 않음
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {

	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;
}
