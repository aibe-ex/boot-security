package org.example.bootsecurity.controller;

import org.example.bootsecurity.model.domain.Memo;
import org.example.bootsecurity.model.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final MemoMapper memoMapper;

    public MainController(@Qualifier("memoMapper") MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "Hello World!");
        memoMapper.insert(new Memo(0L, "메모!", null));
        model.addAttribute("memoList", memoMapper.findAll());
        return "index";
    }
}
