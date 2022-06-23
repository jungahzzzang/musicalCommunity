package com.jungahzzzang.musicalcommunity.musical.repository;

import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MusicalRepository extends JpaRepository<Musical,Long> {

    @Query("SELECT m FROM Musical m")
    Page<Object[]> getListPage(Pageable pageable);
}
