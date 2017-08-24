package com.github.ok3.spocktransactiontest.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(@Param("id") Long memberId);

    List<Member> findByName(@Param("name") String name);
}
