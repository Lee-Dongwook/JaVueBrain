package com.example.backend.domain.member;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
    name = "members",
    uniqueConstraints = @UniqueConstraint(name="uk_member_email", columnNames = "email"),        
    indexes = @Index(name = "idx_member_email", columnList = "email")
)
public class Member {
  @Id
  @Column(name = "uuid", nullable = false, columnDefinition = "uuid")
  private UUID uuid;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
  private OffsetDateTime createdAt;
  
  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz")
  private OffsetDateTime updatedAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false, length = 20)
  private Role role = Role.USER;

  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "profile_image_url", length = 255)
  private String profileImageUrl;

  @Enumerated(EnumType.STRING)
  @Column(name = "auth_provider", nullable = false, length = 20)
  private AuthProvider authProvider;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MemberAgreement> memberAgreements = new ArrayList<>();

    // 다른 도메인들이 Member를 참조하도록 바인딩 예정 (placeholder)
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<?> threads = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<?> categories = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<?> datas = new ArrayList<>();

  protected Member() {}
  
  @PrePersist
  public void prePersist() {
      if (uuid == null)
          uuid = UUID.randomUUID();
  }
}
