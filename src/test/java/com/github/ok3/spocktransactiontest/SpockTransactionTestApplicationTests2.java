package com.github.ok3.spocktransactiontest;

import com.github.ok3.spocktransactiontest.member.Member;
import com.github.ok3.spocktransactiontest.member.MemberRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class SpockTransactionTestApplicationTests2 {
    @Autowired
    MockMvc mvc;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void setup() {
        System.out.println("SETUP: " + memberRepository.findAll().size());
    }

    @Test
    public void contextLoads() throws Exception {
        Member member = new Member();
        member.setName("ddolking");
        ResultActions result = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(member)));
        result.andExpect(status().isOk());
    }

    @Test
    public void contextLoads2() throws Exception {
        Member member = new Member();
        member.setName("ddolking2");
        ResultActions result = mvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(member)));
        result.andExpect(status().isOk());
    }
}
