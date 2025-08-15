package com.example.backend.controller.member;

import com.example.backend.controller.member.dto.MemberCreateReq;
import com.example.backend.controller.member.dto.MemberRes;
import com.example.backend.controller.member.dto.MemberUpdateReq;
import com.example.backend.domain.member.Member;
import com.example.backend.service.MemberService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MemberRes> create(@RequestBody @Valid MemberCreateReq req) {
        Member m = service.create(req.getEmail(), req.getName());
        return ResponseEntity.ok(MemberMapper.toRes(m));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberRes> get(@PathVariable Long id) {
        Member m = service.get(id);
        return ResponseEntity.ok(MemberMapper.toRes(m));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberRes> update(@PathVariable Long id, @RequestBody @Valid MemberUpdateReq req) {
        Member m = service.update(id, req.getName());
        return ResponseEntity.ok(MemberMapper.toRes(m));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
