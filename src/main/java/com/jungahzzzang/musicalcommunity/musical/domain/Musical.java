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

    private String mt20id;  //뮤�?컬코?��
    private String prfnm;   //공연�?
    private String fcltynm; //공연?���?
    private String poster;  //?��?��?�� 경로

    private String prfstate;    //공연 �? ?��?��
}
