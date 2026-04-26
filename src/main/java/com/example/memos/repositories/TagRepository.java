package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memos.models.entities.Tags;

public interface TagRepository extends JpaRepository<Tags, Long> {
	
}
