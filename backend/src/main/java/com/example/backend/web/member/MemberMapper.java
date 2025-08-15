package com.example.backend.web.member;

import com.example.backend.domain.member.Member;
import com.example.backend.web.member.dto.MemberRes;

public class MemberMapper {
    public static MemberRes toRes(Member m) {
        return new MemberRes(m.getId(), m.getEmail(), m.getName(), m.getCreatedAt());
    }
}
