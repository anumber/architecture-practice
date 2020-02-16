package com.microservices.multiplication.service;

import com.microservices.multiplication.domain.Multiplication;
import com.microservices.multiplication.domain.MultiplicationResultAttempt;
import com.microservices.multiplication.domain.User;
import com.microservices.multiplication.event.EventDispatcher;
import com.microservices.multiplication.repository.MultiplicationResultAttemptRepository;
import com.microservices.multiplication.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

/**
 * <p>
 * </p>
 */
class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventDispatcher eventDispatcher;

    @BeforeAll
    public static void init() {

    }

    @AfterAll
    public static void destroy() {

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // With this call to initMocks we tell Mockito to process the annotations
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository, eventDispatcher);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        // then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
    }

    @Test
    void checkCorrectAttemptTest() {
//        given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("liuyihao");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("liuyihao")).willReturn(Optional.empty());
//        when
        boolean checkAttempt = multiplicationServiceImpl.checkAttempt(attempt);

//        then
        assertThat(checkAttempt).isTrue();
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    void checkWrongAttemptTest() {
        //        given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("liuyihao");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010,false);
        given(userRepository.findByAlias("liuyihao")).willReturn(Optional.empty());

//        when
        boolean checkAttempt = multiplicationServiceImpl.checkAttempt(attempt);

//        then
        assertThat(checkAttempt).isFalse();
        verify(attemptRepository).save(attempt);
    }

    @Test
    public void retrieveStatsTest() {
//        given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("liuyihao");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(
                user, multiplication, 3010, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(
                user, multiplication, 3051, false);

        List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);

        given(userRepository.findByAlias("liuyihao")).willReturn(Optional.empty());
        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("liuyihao"))
                .willReturn(latestAttempts);

        // when
        List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImpl.getStatsForUser("liuyihao");

        // then
        assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
    }
}