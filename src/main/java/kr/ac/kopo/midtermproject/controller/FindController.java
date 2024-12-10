package kr.ac.kopo.midtermproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/find")
public class FindController {

    @GetMapping("/user")
    public String find(Model model) {
        return "find";
    }

    @GetMapping("/findId")
    public String findId(Model model) {
        return "findId";
    }

    @GetMapping("/findPw")
    public String findPw(Model model) {
        return "findPw";
    }
}
