package com.example.backend.domain.member;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "members", indexes = {
    @Index(name = "idx_member_email", columnList = "email", unique = true)
})
public class Member {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120, unique = true)
  private String email;

  @Column(nullable = false, length = 60)
  private String name;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  protected Member() {}

  public Member(String email, String name) {
      this.email = email;
      this.name = name;
  }

  public Long getId() {
      return id;
  }

  public String getEmail() {
      return email;
  }

  public String getName() {
      return name;
  }

  public OffsetDateTime getCreatedAt() {
      return createdAt;
  }

  public void changeName(String name) {
      this.name = name;
  }
}
