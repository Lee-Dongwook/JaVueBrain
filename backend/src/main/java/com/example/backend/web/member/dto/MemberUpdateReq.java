package com.example.backend.web.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberUpdateReq {
  @NotBlank @Size(max = 60)
  private String name;

  public String getName() {
      return name;
  }
}
