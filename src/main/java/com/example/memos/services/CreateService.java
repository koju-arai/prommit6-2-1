package com.example.memos.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.memos.repositories.MemoRepository;
import com.example.memos.models.entities.Memos;

@Service
public class CreateService {

  @Autowired
  private MemoRepository memoRepository;
  
  public void save(Memos memo){
	  memoRepository.save(memo);
  }
	
}
