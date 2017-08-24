package com.github.ok3.spocktransactiontest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    Member addMember(Member member) {
        return memberRepository.save(member);
    }
}
