package com.microservices.gamification.service;

import com.microservices.gamification.domain.GameStats;
import com.microservices.gamification.repository.BadgeCardRepository;
import com.microservices.gamification.repository.ScoreCardRepository;

/**
 * @date 2020-02-16
 **/
public class GameServiceImpl implements GameService {

    private final BadgeCardRepository badgeCardRepository;
    private final ScoreCardRepository scoreCardRepository;

    public GameServiceImpl(BadgeCardRepository badgeCardRepository, ScoreCardRepository scoreCardRepository) {
        this.badgeCardRepository = badgeCardRepository;
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        return null;
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        GameStats gameStats = new GameStats();
        return gameStats;
    }
}
