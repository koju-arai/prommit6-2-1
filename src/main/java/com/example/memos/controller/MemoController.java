package com.example.memos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.memos.services.MemoService;
import com.example.memos.services.TagService;
import com.example.memos.models.entities.Memos;
import com.example.memos.models.entities.Tags;
import java.time.LocalDateTime;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;


@Controller
public class MemoController {
  @Autowired
  MemoService memoService;
  @Autowired
  TagService tagService;
  
  
  @GetMapping("/")
  public String getMypage(
    @RequestParam(name = "keyword", required = false) String keyword,
	@RequestParam(name = "tagIds", required = false) List<Long> tagIds,
	@RequestParam(name = "startDate", required = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate,
	@RequestParam(name = "endDate", required = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate,
	@RequestParam(name = "sort", required = false) Integer sort,
    Model model) {
	  
	  List<Memos> memos = memoService.findAll(keyword, tagIds, startDate, endDate, sort);
	  model.addAttribute("memos", memos);
	  model.addAttribute("tags",tagService.findAll());
	  return "memos/list";
	  
  }
  
  @GetMapping("/{id}")
  public String getMemoById(
  @PathVariable(name="id") Long id,
  Model model) {
    Optional<Memos> memos = memoService.findById(id);
    model.addAttribute("memos", memos.orElseThrow());
    return "memos/detail";
  }
  
  @PostMapping("/{id}/delete")
  public String deleteMemo(
  @RequestParam(name="id", required = true) Long id) {
    memoService.deleteById(id);
    return "redirect:/";
  }
  
  @GetMapping("/create")
  public String getCreatePage(Model model) {
	  model.addAttribute("memos", new Memos());
	  model.addAttribute("tags", tagService.findAll());
	  return "memos/create";
  }
  
  @PostMapping("/create")
  public String postCreatePage(
    @ModelAttribute Memos memos,
    @RequestParam(name = "tagIds", required = false) List<Long> tagIds,
    Model model) {
	  memos.setCreatedAt(LocalDateTime.now());
	  memos.setUpdatedAt(LocalDateTime.now());
	  memos.setDeleted(false);
	  
	  if(tagIds != null) {
		Set<Tags> selectedTags = new HashSet<>(tagService.findAllById(tagIds));
		memos.setTags(selectedTags);
	  }
	  
	  memoService.save(memos);
      return "redirect:/";
  }
  
  @GetMapping("/{id}/edit")
  public String getUpdatePage(
    @PathVariable(name="id") Long id,
    Model model) {
	  Memos memos = memoService.findById(id).orElseThrow();
	  model.addAttribute("memos", memos);
	  model.addAttribute("tags", tagService.findAll());
	  return "memos/update";
  }
  @PostMapping("/{id}/edit")
  public String postUpdatePage(
	  @PathVariable(name="id") Long id,
	  @RequestParam(name="tagIds") List<Long> tagIds,
	  @ModelAttribute Memos formMemo, 
	  Model model) {
	  Memos memos = memoService.findById(formMemo.getId()).orElseThrow();
	  memos.setUpdatedAt(LocalDateTime.now());
	  memos.setTitle(formMemo.getTitle());
	  memos.setDetail(formMemo.getDetail());
	  
	  if(tagIds != null) {
		Set<Tags> selectedTags = new HashSet<>(tagService.findAllById(tagIds));
		memos.setTags(selectedTags);
	  }
	  
	  memoService.save(memos);
      return "redirect:/";
  }
}
