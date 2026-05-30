package com.example.memos.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.memos.repositories.TagRepository;
import com.example.memos.models.entities.Tags;

import java.util.List;

@Service
public class TagService {
  @Autowired
  private TagRepository tagRepository;
	
  public List<Tags> findAll(){
    return tagRepository.findAll();
  }
  
  public List<Tags> findAllById(List<Long> id){
	    return tagRepository.findAllById(id);
	  }
}
