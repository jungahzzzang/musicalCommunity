package com.jungahzzzang.musicalcommunity.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO<B,T> {

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDTO(){
        this.page=1;
        this.size=10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1,size,sort);    //?˜?´ì§? ë²ˆí˜¸ê°? 0ë¶??„° ?‹œ?‘?•˜ë¯?ë¡? 1?˜?´ì§??˜ ê²½ìš° 0?´ ?  ?ˆ˜ ?ˆ?„ë¡? page-1
    }
}
