package com.jungahzzzang.musicalcommunity.index;

import com.jungahzzzang.musicalcommunity.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, SessionUser user){

        if(user!=null){
            model.addAttribute("userName",user.getUsername());
        }
        return "index";
    }
}
