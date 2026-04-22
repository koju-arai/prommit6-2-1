package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.memos.models.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Integer> {
}
