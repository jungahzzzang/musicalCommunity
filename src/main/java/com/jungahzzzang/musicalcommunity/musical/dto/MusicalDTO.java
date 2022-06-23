package com.jungahzzzang.musicalcommunity.musical.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicalDTO {

    private Long mcode;
    private String mt20id;  //뮤지컬코드
    private String prfnm;   //공연명
    private String fcltynm; //공연장명
    private String poster;  //포스터 경로
    private String prfstate;    //공연 중 상태
}
