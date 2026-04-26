package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memos.models.entities.Memos;

public interface MemoRepository extends JpaRepository<Memos, Long> {
}
