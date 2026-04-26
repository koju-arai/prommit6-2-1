package com.example.memos.models.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "memos")
public class Memos extends BaseEntity {
  
  @Column(name = "title", length = 255, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false, name = "body")
  private String detail;
  
  @ManyToMany
  @JoinTable(
      name = "memo_tags",
      joinColumns = @JoinColumn(name = "memo_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tags> tags = new ArrayList<>();
  
}
