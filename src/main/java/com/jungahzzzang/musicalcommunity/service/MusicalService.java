package com.jungahzzzang.musicalcommunity.service;

import com.jungahzzzang.musicalcommunity.api.MusicalApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicalService {

    private final MusicalApi musicalApi;

    public void insertMusical(){
        musicalApi.callMusicalApiJson();
    }
}
