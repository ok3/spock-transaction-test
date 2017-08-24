package com.github.ok3.spocktransactiontest

import com.github.ok3.spocktransactiontest.member.Member
import com.github.ok3.spocktransactiontest.member.MemberRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.annotation.PostConstruct

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class MemberControllerSpec2 extends Specification {

    @Autowired
    MockMvc mvc

    RestTemplate restTemplate

    @Autowired
    MemberRepository memberRepository

    @PostConstruct
    def init() {
        restTemplate = new RestTemplate(new MockMvcClientHttpRequestFactory(mvc))
    }

    def setup() {
        println("SETUP: " + memberRepository.findAll().size())
    }

    def "test repository"() {
        when:
        def result = restTemplate.getForEntity("/members", List)

        then:
        result.statusCode == HttpStatus.OK
    }

    def "create member"() {
        given:
        def data = new Member()
        data.name = "Charlie"

        when:
        def result = restTemplate.postForEntity('/members', data, Member)

        then:
        result.statusCode == HttpStatus.OK
        result.body.name == "Charlie"
    }

}