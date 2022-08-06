package com.jungahzzzang.musicalcommunity.board.service;

import org.springframework.stereotype.Service;

import com.jungahzzzang.musicalcommunity.board.domain.Board;
import com.jungahzzzang.musicalcommunity.board.dto.BoardDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageResultDTO;
import com.jungahzzzang.musicalcommunity.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository repository;
	
	@Override
	public Long register(BoardDTO dto) {
		log.info("DTO--------------------");
		log.info(dto);
		
		Board board = dtoToEntity(dto);
		log.info(board);
		repository.save(board);
		
		return board.getPostId();
		
	}

	@Override
	public PageResultDTO<BoardDTO, Board> getBoardList(PageRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO read(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(BoardDTO dto) {
		// TODO Auto-generated method stub
		
	}
	
	

}
