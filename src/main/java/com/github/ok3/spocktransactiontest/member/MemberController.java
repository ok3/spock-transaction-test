package com.github.ok3.spocktransactiontest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }
}
