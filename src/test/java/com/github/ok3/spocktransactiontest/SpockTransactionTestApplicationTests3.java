package com.github.ok3.spocktransactiontest;

import com.github.ok3.spocktransactiontest.member.Member;
import com.github.ok3.spocktransactiontest.member.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class SpockTransactionTestApplicationTests3 {
    @Autowired
    MockMvc mvc;

    private RestTemplate restTemplate;

    @Autowired
    MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate(new MockMvcClientHttpRequestFactory(mvc));
    }

    @Before
    public void setup() {
        System.out.println("SETUP: " + memberRepository.findAll().size());
    }

    @Test
    public void contextLoads() throws Exception {
        Member member = new Member();
        member.setName("Honey");
        ResponseEntity<Member> result = restTemplate.postForEntity("/members", member, Member.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Honey", result.getBody().getName());
        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void contextLoads2() throws Exception {
        Member member = new Member();
        member.setName("Isabella");
        ResponseEntity<Member> result = restTemplate.postForEntity("/members", member, Member.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Isabella", result.getBody().getName());
        memberRepository.findAll().forEach(System.out::println);
    }
}
