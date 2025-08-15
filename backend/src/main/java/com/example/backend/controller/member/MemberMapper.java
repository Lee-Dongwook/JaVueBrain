package com.example.backend.controller.member;

import com.example.backend.controller.member.dto.MemberRes;
import com.example.backend.domain.member.Member;

public class MemberMapper {
    public static MemberRes toRes(Member m) {
        return new MemberRes(m.getId(), m.getEmail(), m.getName(), m.getCreatedAt());
    }
}
