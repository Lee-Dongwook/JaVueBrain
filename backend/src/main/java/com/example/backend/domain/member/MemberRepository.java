package com.example.backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface MemberRepository extends JpaRepository<Member, UUID> {
    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
