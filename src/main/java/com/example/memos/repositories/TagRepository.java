package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.memos.models.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
	
}
