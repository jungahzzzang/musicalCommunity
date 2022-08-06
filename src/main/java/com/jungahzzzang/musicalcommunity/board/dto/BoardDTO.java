package com.jungahzzzang.musicalcommunity.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	
	private Long postId;
	private Long mberId;
	private String username;
	private String email;
	private String title;
	private String content;
	private LocalDateTime regDate, modDate;

}
