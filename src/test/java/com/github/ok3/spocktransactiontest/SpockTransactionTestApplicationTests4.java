package com.github.ok3.spocktransactiontest;

import com.github.ok3.spocktransactiontest.member.Member;
import com.github.ok3.spocktransactiontest.member.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class SpockTransactionTestApplicationTests4 {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void setup() {
        System.out.println("SETUP: " + memberRepository.findAll().size());
    }

    @Test
    public void contextLoads() throws Exception {
        Member member = new Member();
        member.setName("John");
        ResponseEntity<Member> result = restTemplate.postForEntity("/members", member, Member.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("John", result.getBody().getName());
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void contextLoads2() throws Exception {
        Member member = new Member();
        member.setName("Kelly");
        ResponseEntity<Member> result = restTemplate.postForEntity("/members", member, Member.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Kelly", result.getBody().getName());
        memberRepository.findAll().forEach(System.out::println);
    }
}
