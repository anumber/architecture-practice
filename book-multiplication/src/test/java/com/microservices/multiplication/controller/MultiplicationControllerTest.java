package com.microservices.multiplication.controller;

import com.microservices.multiplication.domain.Multiplication;
import com.microservices.multiplication.service.MultiplicationService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;

/**
 * <p>
 * </p>
 */
@WebMvcTest(MultiplicationController.class)
@AutoConfigureJsonTesters
class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Multiplication> jsonMultiplication;


    @Test
    public void getRandomMultiplicationTest() throws Exception {
//        given
        given(multiplicationService.createRandomMultiplication()).willReturn(new Multiplication(70, 20));

        RestAssuredMockMvc
                .given()
                    .mockMvc(mockMvc)
                    .accept(MediaType.APPLICATION_JSON)
                .when()
                    .get("/multiplications/random")
                .then()
                    .status(OK)
                    .body(equalTo(jsonMultiplication.write(new Multiplication(70, 20)).getJson()));

    }
}