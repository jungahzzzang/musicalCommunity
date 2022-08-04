package com.jungahzzzang.musicalcommunity.musical.repository;

import com.jungahzzzang.musicalcommunity.musical.domain.Musical;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MusicalRepository extends JpaRepository<Musical,Long> {

	
	//상영중
    @Query("SELECT m FROM Musical m WHERE m.prfstate='공연중'")
    Page<Object[]> getIngListPage(Pageable pageable);
    
    //상영예정
    @Query("SELECT m FROM Musical m WHERE m.prfstate='공연예정'")
    Page<Object[]> getExpecListPage(Pageable pageable);
    
    //특정 뮤지컬 조회
    @Query("SELECT m FROM Musical m WHERE m.mcode = :mcode")
    List<Object[]> getMusicalInfo(@Param("mcode") Long mcode);
}
