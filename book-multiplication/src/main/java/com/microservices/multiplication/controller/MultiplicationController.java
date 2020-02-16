package com.microservices.multiplication.controller;

import com.microservices.multiplication.domain.Multiplication;
import com.microservices.multiplication.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *     展示层
 * </p>
 */
@RestController
@RequestMapping("/multiplications")
@Slf4j
public class MultiplicationController {

    private final MultiplicationService multiplicationService;

    public MultiplicationController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("/random")
    Multiplication getRandomMultiplication() {
        return multiplicationService.createRandomMultiplication();
    }
}