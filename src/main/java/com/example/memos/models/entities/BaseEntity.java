package com.example.memos.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, name = "is_deleted")
  private boolean isDeleted; 
  
  @Column(nullable = false, name = "created_at")
  private LocalDateTime createdAt;
  
  @Column(nullable = false, name = "updated_at")
  private LocalDateTime updatedAt;
  
  
  
}