package com.jungahzzzang.musicalcommunity.musical.service;

import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import com.jungahzzzang.musicalcommunity.musical.dto.MusicalDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageResultDTO;

import java.util.HashMap;
import java.util.Map;


public interface MusicalService {
	
    public void insertMusical();
    
    //목록 처리
    public PageResultDTO<MusicalDTO,Object[]> getList(PageRequestDTO requestDTO);
    
    //조회 처리
    MusicalDTO getMusical(Long mcode);

    default MusicalDTO entitiesToDTO(Musical musical){
        MusicalDTO musicalDTO = MusicalDTO.builder()
                .mcode(musical.getMcode())
                .prfnm(musical.getPrfnm())
                .fcltynm(musical.getFcltynm())
                .poster(musical.getPoster())
                .prfstate(musical.getPrfstate())
                .build();

        return musicalDTO;
    }

    default Map<String,Object> dtoToEntity(MusicalDTO musicalDTO){
        Map<String,Object> entityMap = new HashMap<>();

        Musical musical = Musical.builder()
                .mcode(musicalDTO.getMcode())
                .prfnm(musicalDTO.getPrfnm())
                .fcltynm(musicalDTO.getFcltynm())
                .poster(musicalDTO.getPoster())
                .prfstate(musicalDTO.getPrfstate())
                .build();
        entityMap.put("musical",musical);

        return entityMap;
    }

}
