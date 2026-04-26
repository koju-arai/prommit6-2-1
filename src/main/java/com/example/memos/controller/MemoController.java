package com.example.memos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.memos.services.CreateService;
import com.example.memos.models.entities.Memos;
import java.time.LocalDateTime;

@Controller
public class MemoController {
  @Autowired
  CreateService createService;  
  
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
	  createService.save(memos);
      return "redirect:/";
  }
  
}
