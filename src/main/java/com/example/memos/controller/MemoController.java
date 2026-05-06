package com.example.memos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.memos.services.MemoService;
import com.example.memos.models.entities.Memos;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

@Controller
public class MemoController {
  @Autowired
  MemoService memoService;  
  
  @GetMapping("/")
  public String getMypage(
    @RequestParam(required = false) String keyword,
    @RequestParam(required = false) List<Long> tagIds,
    @RequestParam(required = false) LocalDateTime startAt,
    @RequestParam(required = false) LocalDateTime endAt,
    Model model) {
	  List<Memos> memos = memoService.findMemo(keyword, tagIds, startAt, endAt);
	  return "memos/list";
  }
  
  @GetMapping("/create")
  public String getCreatePage(Model model) {
	  model.addAttribute("memos", new Memos());
	  return "createMemo";
  }
  
  @PostMapping("/create")
  public String postCreatePage(@ModelAttribute Memos memos, Model model) {
	  memos.setCreatedAt(LocalDateTime.now());
	  memos.setUpdatedAt(LocalDateTime.now());
	  memos.setDeleted(false);
	  memoService.save(memos);
      return "redirect:/";
  }
  
}
