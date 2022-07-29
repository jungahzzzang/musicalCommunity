package com.jungahzzzang.musicalcommunity.musical.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Musical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcode")
    private Long mcode;

    private String mt20id;  //ë®¤ì?ì»¬ì½”?“œ
    private String prfnm;   //ê³µì—°ëª?
    private String fcltynm; //ê³µì—°?¥ëª?
    private String poster;  //?¬?Š¤?„° ê²½ë¡œ

    private String prfstate;    //ê³µì—° ì¤? ?ƒ?ƒœ
}
