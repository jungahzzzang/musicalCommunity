package com.jungahzzzang.musicalcommunity.musical.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {

    //DTO ë¦¬ìŠ¤?Š¸
    private List<DTO> dtoList;

    //ì´? ?˜?´ì§? ë²ˆí˜¸
    private int totalPage;

    //?˜„?¬ ?˜?´ì§? ë²ˆí˜¸
    private int page;
    //ëª©ë¡ ?‚¬?´ì¦?
    private int size;
    //?‹œ?‘ ?˜?´ì§? ë²ˆí˜¸,? ?˜?´ì§? ë²ˆí˜¸
    private int start, end;
    //?´? „,?‹¤?Œ
    private boolean prev, next;
    //?˜?´ì§? ë²ˆí˜¸ ëª©ë¡
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result){
        dtoList = (List<DTO>) result.stream().collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page=pageable.getPageNumber()+1;   //0ë¶??„° ?‹œ?‘?•˜ë¯?ë¡?
        this.size=pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0))*10;
        start = tempEnd-9;
        prev=start>1;
        end=totalPage>tempEnd?tempEnd:totalPage;
        next=totalPage>tempEnd;
        pageList= IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
    }
}
