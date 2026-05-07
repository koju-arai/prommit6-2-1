package com.example.memos.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.memos.repositories.MemoRepository;
import com.example.memos.models.entities.BaseEntity;
import com.example.memos.models.entities.Memos;
import org.springframework.data.jpa.domain.Specification;
import com.example.memos.specification.MemoSpecification;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemoService {

  @Autowired
  private MemoRepository memoRepository;
  
  public List<Memos> findAll(){
	return memoRepository.findAll();
  }
  
  public Optional<Memos> findById(Long id){
	return memoRepository.findById(id);
  }
  
  public void deleteById(Long id) {
	Memos memo = memoRepository.findById(id).orElseThrow();
	memo.setDeleted(true);
	memoRepository.save(memo);
  }
  
  public void save(Memos memo){
	memoRepository.save(memo); 
  }

  public List<Memos> findMemo(String keyword, List<Long>tagIds, LocalDateTime startAt, LocalDateTime endAt){

    Specification<Memos> spec = MemoSpecification.isNotDeleted();
  
    if (keyword != null && !keyword.isBlank()) {
      spec = spec.and(MemoSpecification.containsKey(keyword));
    }
    if (tagIds != null && !tagIds.isEmpty()) {
      spec = spec.and(MemoSpecification.hasTag(tagIds));
    }
    if (startAt != null) {
      spec = spec.and(MemoSpecification.createdAtAfter(startAt));
    }
    if (endAt != null) {
      spec = spec.and(MemoSpecification.createdAtAfter(endAt));
    }
  
    return memoRepository.findAll(spec);
  
  }
  
}
