package com.example.backend.service;

import com.example.backend.common.exception.NotFoundException;
import com.example.backend.domain.member.Member;
import com.example.backend.domain.member.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

public class MemberService {
    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public Member create(String email, String name) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        return repo.save(new Member(email, name));
    }

    @Transactional(readOnly=true)
    public Member get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("회원이 없습니다."));
    }

    public Member update(Long id, String name) {
        Member m = get(id);
        m.changeName(name);
        return m;
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new NotFoundException("회원이 없습니다.");
        repo.deleteById(id);
    }
}
