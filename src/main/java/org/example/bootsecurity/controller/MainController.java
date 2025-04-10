package org.example.bootsecurity.controller;

import org.example.bootsecurity.model.domain.Memo;
import org.example.bootsecurity.model.domain.MemoForm;
import org.example.bootsecurity.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    private final MemoService memoService;

    public MainController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("memoList", memoService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("memoForm", new MemoForm());
        return "add";
    }

    @PostMapping("/add")
    public String save(MemoForm form) throws Exception {
//        Memo memo = new Memo(0L, form.getText(), "");
        Memo memo = Memo.fromText(form.getText());
        memoService.create(memo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        memoService.deleteById(id);
        String msg = "%d 삭제 완료".formatted(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }

    @PostMapping("/delete-all")
    public String deleteAll(RedirectAttributes redirectAttributes) {
        memoService.deleteAll();
        redirectAttributes.addFlashAttribute("msg", "전체 삭제 완료");
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Memo memo = memoService.findById(id);
        model.addAttribute("memo", memo);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @RequestParam("text") String text, RedirectAttributes redirectAttributes) {
        Memo oldMemo = memoService.findById(id);
        Memo newMemo = new Memo(oldMemo.id(), text, oldMemo.createdAt());
        memoService.update(newMemo);
        String msg = "%d 수정 완료".formatted(id);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }
}
