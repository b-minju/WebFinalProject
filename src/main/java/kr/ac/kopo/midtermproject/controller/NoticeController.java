package kr.ac.kopo.midtermproject.controller;

import kr.ac.kopo.midtermproject.dto.NoticeDTO;
import kr.ac.kopo.midtermproject.dto.PageRequestDTO;
import kr.ac.kopo.midtermproject.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice/")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", noticeService.getList(pageRequestDTO));
    }

    @GetMapping("/write")
    public void write() {

    }

    @PostMapping("/write")
    public String writeNotice(NoticeDTO dto, RedirectAttributes redirectAttributes) {
        Long num = noticeService.register(dto);
        redirectAttributes.addFlashAttribute("msg", num);
        return "redirect:/notice/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long num, Model model) {
        NoticeDTO noticeDTO = noticeService.get(num);
        model.addAttribute("dto", noticeDTO);
    }

    @PostMapping("/remove")
    public String remove(Long num, RedirectAttributes redirectAttributes) {
        noticeService.remove(num);
        redirectAttributes.addFlashAttribute("msg", num);
        return "redirect:/notice/list";
    }

    @PostMapping("/modify")
    public String modify(NoticeDTO noticeDTO,
                         @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        noticeService.modify(noticeDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("num", noticeDTO.getNum());

        return "redirect:/notice/read";
    }
}
