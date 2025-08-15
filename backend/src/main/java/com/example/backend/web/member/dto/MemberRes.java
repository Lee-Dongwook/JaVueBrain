package com.example.backend.web.member.dto;

import java.time.OffsetDateTime;

public class MemberRes {
    private Long id;
    private String email;
    private String name;
    private OffsetDateTime createdAt;
    
    public MemberRes(Long id, String email, String name, OffsetDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
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
}
