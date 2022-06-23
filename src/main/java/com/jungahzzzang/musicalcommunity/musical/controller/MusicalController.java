package com.jungahzzzang.musicalcommunity.musical.controller;

import com.jungahzzzang.musicalcommunity.musical.domain.Musical;
import com.jungahzzzang.musicalcommunity.musical.dto.PageRequestDTO;
import com.jungahzzzang.musicalcommunity.musical.service.MusicalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/musical")
@Log4j2
@RequiredArgsConstructor
public class MusicalController {

    private final MusicalService musicalService;

    @GetMapping("list")
    public void list(Model model, PageRequestDTO pageRequestDTO, Musical musical){

        model.addAttribute("result",musicalService.getList(pageRequestDTO));
    }
}
