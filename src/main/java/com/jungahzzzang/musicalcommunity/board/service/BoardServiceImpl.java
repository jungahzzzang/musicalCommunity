package com.jungahzzzang.musicalcommunity.board.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jungahzzzang.musicalcommunity.board.domain.Board;
import com.jungahzzzang.musicalcommunity.board.domain.QBoard;
import com.jungahzzzang.musicalcommunity.board.dto.BoardDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.board.dto.PageResultDTO;
import com.jungahzzzang.musicalcommunity.board.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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

	//게시글 목록 처리
	@Override
	public PageResultDTO<BoardDTO, Board> getBoardList(PageRequestDTO requestDTO) {
		
		Pageable pageable = requestDTO.getPageable(Sort.by("postId").descending());
		
		//검색 조건 처리
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		
		Page<Board> result = repository.findAll(booleanBuilder,pageable);
		Function<Board, BoardDTO> fn = (board->entityToDto(board));
		
		return new PageResultDTO<>(result, fn);
	}

	//게시글 조회 처리
	@Override
	public BoardDTO read(Long postId) {
		
		Optional<Board> result = repository.findById(postId);
		return result.isPresent()? entityToDto(result.get()) : null;
	}

	//게시글 삭제 처리
	@Override
	public void remove(Long postId) {
		
		repository.deleteById(postId);
		
	}

	//게시글 수정 처리
	@Override
	public void modify(BoardDTO dto) {
		
		//수정 항목은 제목, 내용
		Optional<Board> result = repository.findById(dto.getPostId());
		
		if(result.isPresent()) {
			Board board = result.get();
			
			board.changeTitle(dto.getTitle());
			board.changeContent(dto.getContent());
			
			repository.save(board);
		}
		
	}
	
	//Querydsl 검색 처리
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		
		String type = requestDTO.getType();
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QBoard qBoard = QBoard.board;
		String keyword = requestDTO.getKeyword();
		
		//조건 생성
		BooleanExpression expression = qBoard.postId.gt(0L);
		booleanBuilder.and(expression);
		if(type == null || type.trim().length() == 0) {
			//검색 조건이 없는 경우
			return booleanBuilder;
		}
		
		//검색 조건 작성
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		if(type.contains("t")) {
			conditionBuilder.or(qBoard.title.contains(keyword));
		}
		
		if(type.contains("c")) {
			conditionBuilder.or(qBoard.content.contains(keyword));
		}
		
		if(type.contains("w")) {
			conditionBuilder.or(qBoard.member.username.contains(keyword));
		}
		
		//모든 조건 통합
		booleanBuilder.and(conditionBuilder);
		return booleanBuilder;
	}
	
	

}
