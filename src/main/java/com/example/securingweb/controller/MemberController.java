package com.example.securingweb.controller;

import com.example.securingweb.entity.Member;
import com.example.securingweb.entity.Role;
import com.example.securingweb.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public Member create(@RequestBody Member member) {
        member.setRole(Role.USER);
        return memberService.save(member);
    }

}
