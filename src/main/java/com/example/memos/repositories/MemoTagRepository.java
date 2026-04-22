package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.memos.models.entity.MemoTagEntity;

public interface MemoTagRepository extends JpaRepository<MemoTagEntity, Integer> {

}
