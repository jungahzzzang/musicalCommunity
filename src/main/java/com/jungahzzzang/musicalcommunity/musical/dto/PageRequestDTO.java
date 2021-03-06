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
        return PageRequest.of(page-1,size,sort);    //??΄μ§? λ²νΈκ°? 0λΆ??° ???λ―?λ‘? 1??΄μ§?? κ²½μ° 0?΄ ?  ? ??λ‘? page-1
    }
}
