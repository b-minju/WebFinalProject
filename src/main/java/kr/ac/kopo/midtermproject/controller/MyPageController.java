package kr.ac.kopo.midtermproject.controller;

import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {
    @GetMapping("/my_page")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }
}
