package com.microservices.multiplication.controller;

import com.microservices.multiplication.domain.Multiplication;
import com.microservices.multiplication.domain.MultiplicationResultAttempt;
import com.microservices.multiplication.domain.User;
import com.microservices.multiplication.service.MultiplicationService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;

/**
 * <p>
 * </p>
 */
@WebMvcTest(MultiplicationResultAttemptController.class)
@AutoConfigureJsonTesters
class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<MultiplicationResultAttempt> jsonResultAttempt;

    @Autowired
    private JacksonTester<List<MultiplicationResultAttempt>> jsonResultAttemptList;

    @Test
    public void postResultReturnCorrect() throws IOException {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws IOException {
        genericParameterizedTest(false);
    }

    public void genericParameterizedTest(final boolean correct) throws IOException {
        given(multiplicationService.checkAttempt(ArgumentMatchers.any(MultiplicationResultAttempt.class))).willReturn(correct);
        User user = new User("liuyihao");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);
        RestAssuredMockMvc
                .given()
                    .mockMvc(mockMvc)
                    .contentType(ContentType.JSON)
                    .body(attempt)
                .when()
                    .post("/results")
                .then()
                    .status(OK)
                    .body(equalTo(jsonResultAttempt.write(new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), correct)).getJson()));
    }

    @Test
    void getStatisticsTest() throws IOException {
//        given
        User user = new User("liuyihao");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, true);
        List<MultiplicationResultAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);

        given(multiplicationService.getStatsForUser("liuyihao")).willReturn(recentAttempts);

//        when
        RestAssuredMockMvc
                .given()
                    .mockMvc(mockMvc)
                    .param("alias", "liuyihao")
                .when()
                    .get("/results")
                .then()
                    .status(OK)
                    .body(equalTo(jsonResultAttemptList.write(recentAttempts).getJson()));
    }
}