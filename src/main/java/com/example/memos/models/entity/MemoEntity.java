package com.example.memos.models.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "memos")
public class MemoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "title", length = 255, nullable = false)
  private String title;
  
  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt;
  
  @Column(nullable = false, name = "updated_at")
  private LocalDateTime updatedAt;
  
  @Column(columnDefinition = "TEXT", nullable = false, name = "updated_at")
  private String body;
  
  @OneToMany(mappedBy = "memo")
  private List<MemoTagEntity> memoTags = new ArrayList<>();
}
