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
    private String mt20id;  //뮤�?컬코?��
    private String prfnm;   //공연�?
    private String fcltynm; //공연?���?
    private String poster;  //?��?��?�� 경로
    private String prfstate;    //공연 �? ?��?��
}
