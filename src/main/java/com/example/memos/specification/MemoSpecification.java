package com.example.memos.specification;

import org.springframework.data.jpa.domain.Specification;
import com.example.memos.models.entities.Memos;
import com.example.memos.models.entities.Tags;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.criteria.Join;

public class MemoSpecification {
  public static Specification<Memos> isNotDeleted() {
    return (root, query, criteriaBuilder) ->
           criteriaBuilder.isFalse(root.get("isDeleted"));
  }

  public static Specification<Memos> containsKey(String keyword) {
    
	String escapedKeyword = escapeKeyword(keyword);
	  
	return (root, query, criteriaBuilder) ->
      criteriaBuilder.like(root.get("title"), "%" + escapedKeyword + "%", '\\');
  }
  
  public static Specification<Memos> createdAtAfter(LocalDateTime startDateTime) {
	return (root, query, criteriaBuilder) ->
	  criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), startDateTime);
  }
  
  public static Specification<Memos> createdAtBefore(LocalDateTime endDateTime) {
	return (root, query, criteriaBuilder) ->
	  criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), endDateTime);
  }
  
  public static Specification<Memos> hasTag(List<Long> tagIds) {
	return (root, query, criteriaBuilder) ->{
	  query.distinct(true);
	  Join<Memos, Tags> tagsJoin = root.join("tags");
	  return tagsJoin.get("id").in(tagIds);
	};
  }
  
  public static String escapeKeyword(String keyword) {
    return keyword
    	     .replace("\\", "\\\\")
    	     .replace("%", "\\%")
    	     .replace("_", "\\_");
  }
}
