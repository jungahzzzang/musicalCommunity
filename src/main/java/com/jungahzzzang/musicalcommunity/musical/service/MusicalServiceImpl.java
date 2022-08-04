package com.jungahzzzang.musicalcommunity.musical.service;

import com.jungahzzzang.musicalcommunity.api.MusicalApi;
import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import com.jungahzzzang.musicalcommunity.musical.dto.MusicalDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageResultDTO;
import com.jungahzzzang.musicalcommunity.musical.repository.MusicalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MusicalServiceImpl implements  MusicalService{

    private final MusicalApi musicalApi;
    private final MusicalRepository musicalRepository;


    public void insertMusical(){
        musicalApi.callMusicalApiJson();
    }

    @Override
    public PageResultDTO<MusicalDTO,Object[]> getIngList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("mcode").descending());
        Page<Object[]> result = musicalRepository.getIngListPage(pageable);

        return new PageResultDTO<>(result);
    }
    
    @Override
	public PageResultDTO<MusicalDTO, Object[]> getExpecList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("mcode").descending());
        Page<Object[]> result = musicalRepository.getExpecListPage(pageable);
        
        return new PageResultDTO<>(result);
	}

	@Override
	public MusicalDTO getMusical(Long mcode) {
		
		
		List<Object[]> result = musicalRepository.getMusicalInfo(mcode);
		
		Musical musical = (Musical) result.get(0)[0];	//Musical 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값
		
		return entitiesToDTO(musical);
	}

	
}
