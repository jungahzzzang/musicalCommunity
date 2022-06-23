package com.jungahzzzang.musicalcommunity.musical.service;

import com.jungahzzzang.musicalcommunity.api.MusicalApi;
import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import com.jungahzzzang.musicalcommunity.musical.dto.MusicalDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.musical.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
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
    public PageResultDTO<MusicalDTO,Object[]> getList(PageRequestDTO requestDTO){
        Pageable pageable = requestDTO.getPageable(Sort.by("mcode").descending());
        Page<Object[]> result = musicalRepository.getListPage(pageable);

        return new PageResultDTO<>(result);
    }
}
