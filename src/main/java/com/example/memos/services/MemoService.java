package com.example.memos.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.memos.repositories.MemoRepository;
import com.example.memos.models.entities.Memos;
import org.springframework.data.jpa.domain.Specification;
import com.example.memos.specification.MemoSpecification;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

@Service
public class MemoService {

  @Autowired
  private MemoRepository memoRepository;
  
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

  public List<Memos> findAll(String keyword, List<Long>tagIds, LocalDate startDate, LocalDate endDate, Integer sort){

    Specification<Memos> spec = MemoSpecification.isNotDeleted();
    
    if (keyword != null && !keyword.isBlank()) {
      spec = spec.and(MemoSpecification.containsKey(keyword));
    }
    
    if (tagIds != null && !tagIds.isEmpty()) {
      spec = spec.and(MemoSpecification.hasTag(tagIds));
    }
    
    if (startDate != null) {
   	  LocalDateTime startDateTime = startDate.atStartOfDay();;
      spec = spec.and(MemoSpecification.createdAtAfter(startDateTime));
    }
    
    if (endDate != null) {
      LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();
      spec = spec.and(MemoSpecification.createdAtBefore(endDateTime));
    }
    
    Sort sortCondition = createSort(sort);
    
    return memoRepository.findAll(spec, sortCondition);
  }
  
  public Sort createSort(Integer sort) {

	if(sort != null && sort == 0) {
	  return Sort.by(Sort.Direction.ASC, "updatedAt");
	}

    if(sort != null && sort == 1) {
	  return Sort.by(Sort.Direction.DESC, "updatedAt");
	}

	return Sort.by(Sort.Direction.DESC, "updatedAt");
	
  }
}
