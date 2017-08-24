package com.github.ok3.spocktransactiontest

import com.github.ok3.spocktransactiontest.member.Member
import com.github.ok3.spocktransactiontest.member.MemberRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class MemberControllerSpec extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    MemberRepository memberRepository

    def setup() {
        println("SETUP: " + memberRepository.findAll().size())
    }

    def "test repository"() {
        when:
        def result = mvc.perform(get("/members"))

        then:
        result.andExpect(status().isOk())
    }

    def "create member"() {
        given:
        def data = new Member()
        data.name = "chibyeok.ok"

        when:
        def result = mvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(data)))

        then:
        result.andExpect(status().isOk())
    }

}