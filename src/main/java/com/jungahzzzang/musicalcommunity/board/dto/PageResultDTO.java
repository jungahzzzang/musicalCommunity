package com.jungahzzzang.musicalcommunity.board.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO,EN> {

    //DTO 리스?��
    private List<DTO> dtoList;

    //�? ?��?���? 번호
    private int totalPage;

    //?��?�� ?��?���? 번호
    private int page;
    //목록 ?��?���?
    private int size;
    //?��?�� ?��?���? 번호,?�� ?��?���? 번호
    private int start, end;
    //?��?��,?��?��
    private boolean prev, next;
    //?��?���? 번호 목록
    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result){
        dtoList = (List<DTO>) result.stream().collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page=pageable.getPageNumber()+1;   //0�??�� ?��?��?���?�?
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
