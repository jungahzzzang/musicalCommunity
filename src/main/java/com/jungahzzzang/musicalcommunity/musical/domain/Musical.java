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

    private String mt20id;  //공연ID
    private String prfnm;   //공연명
    private String fcltynm; //공연시설명
    private String poster;  //공연포스터 경로

    private String prfstate;    //공연 상태
}
