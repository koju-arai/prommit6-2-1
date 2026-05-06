package com.example.memos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.memos.models.entities.Memos;
import java.util.ArrayList;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memos, Long> , JpaSpecificationExecutor<Memos>{
  
}
