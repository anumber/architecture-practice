package com.microservices.gamification.service;

import com.microservices.gamification.domain.Badge;
import com.microservices.gamification.domain.GameStats;
import com.microservices.gamification.repository.BadgeCardRepository;
import com.microservices.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * note: 写服务的单元测试，就是写系统提供的功能的用例
 * note: 用when/given/then书写测试用例更容易理解（BDD）
 **/
class GameServiceImplTest {

    private GameServiceImpl gameService;
    @Mock
    private BadgeCardRepository badgeCardRepository;
    @Mock
    private ScoreCardRepository scoreCardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // With this call to initMocks we tell Mockito to process the annotations
        gameService = new GameServiceImpl(badgeCardRepository, scoreCardRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void processFirstCorrectAttemptTest() {
//        given
        Long userId = 1L;
        Long attemptId = 8L;

//        when
        GameStats gameStats = gameService.newAttemptForUser(userId, attemptId, true);

//        then
        assertThat(gameStats.getBadges()).containsOnly(Badge.FIRST_WON);
    }

    @Test
    public void processCorrectAttemptForScoreBadgeTest() {

    }

    @Test
    public void processCorrectAttemptForLuckyNumberBadgeTest() {

    }

    @Test
    public void processWrongAttemptTest() {

    }

    @Test
    @DisplayName("检索用户状态")
    public void retrieveStatsForUserTest() {
//        given 有一个用户
        Long userId = 1L;
//        when
        GameStats gameStats = gameService.retrieveStatsForUser(userId);
//        then
        assertThat(gameStats.getBadges()).containsOnly(Badge.SILVER_MULTIPLICATOR);
    }


}