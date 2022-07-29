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
    private String mt20id;  //ë®¤ì?ì»¬ì½”?“œ
    private String prfnm;   //ê³µì—°ëª?
    private String fcltynm; //ê³µì—°?¥ëª?
    private String poster;  //?¬?Š¤?„° ê²½ë¡œ
    private String prfstate;    //ê³µì—° ì¤? ?ƒ?ƒœ
}
