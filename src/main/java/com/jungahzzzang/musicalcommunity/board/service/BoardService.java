package com.jungahzzzang.musicalcommunity.board.service;

import com.jungahzzzang.musicalcommunity.board.domain.Board;
import com.jungahzzzang.musicalcommunity.board.dto.BoardDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageResultDTO;
import com.jungahzzzang.musicalcommunity.member.domain.Member;

public interface BoardService {
	
	public Long register(BoardDTO dto);
	
	
	public PageResultDTO<BoardDTO, Board> getBoardList(PageRequestDTO requestDTO);
	
	public BoardDTO read(Long postId);
	
	public void remove(Long postId);
	
	public void modify(BoardDTO dto);
	
	default Board dtoToEntity(BoardDTO dto) {
		Board board = Board.builder()
				.postId(dto.getPostId())
				.member(Member.builder().mberId(dto.getMberId()).build())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		
		return board;
	}
	
	default BoardDTO entityToDto(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.postId(board.getPostId())
				.mberId(board.getMember().getMberId())
				.username(board.getMember().getUsername())
				.email(board.getMember().getEmail())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.build();
		
		return boardDTO;
	}
	

}
