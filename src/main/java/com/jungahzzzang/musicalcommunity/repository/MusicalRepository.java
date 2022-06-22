package com.jungahzzzang.musicalcommunity.repository;

import com.jungahzzzang.musicalcommunity.domain.Musical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MusicalRepository extends JpaRepository<Musical, Long> {


    //페이징 처리
    //@Query("")
}
