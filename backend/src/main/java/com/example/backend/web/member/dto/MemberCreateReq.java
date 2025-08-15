package com.example.backend.web.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberCreateReq {
    @Email @NotBlank
    private String email;

    @NotBlank @Size(max = 60)
    private String name;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
