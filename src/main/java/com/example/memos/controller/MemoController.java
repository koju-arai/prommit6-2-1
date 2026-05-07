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
import java.util.Optional;

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
	  model.addAttribute("memos", memos);
	  return "memos/list";
  }
  
  @GetMapping("/detail")
  public String getMemoById(
  @RequestParam(required = true) Long id,
  Model model) {
    Optional<Memos> memos = memoService.findById(id);
    model.addAttribute("memos", memos.orElseThrow());
    return "memo/detail";
  }
  
  @GetMapping("/delete")
  public String deleteMemo(
  @RequestParam(required = true) Long id) {
    memoService.deleteById(id);
    return "redirect:/";
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
  
  @GetMapping("/update")
  public String getUpdatePage(
    @RequestParam(required = true) Long id,
    Model model) {
	  Memos memos = memoService.findById(id).orElseThrow();
	  model.addAttribute("memos", memos);
	  return "updateMemo";
  }
  @PostMapping("/update")
  public String postUpdatePage(@ModelAttribute Memos memos, Model model) {
	  memos.setUpdatedAt(LocalDateTime.now());
	  memoService.save(memos);
      return "redirect:/";
  }
  
}
