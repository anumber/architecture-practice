package com.microservices.gamification.service;

import com.microservices.gamification.domain.LeaderBoardRow;

import java.util.List;

/**
 * Provides methods to access the LeaderBoard with users and scores.
 *
 * @date 2020-02-16
 **/
public interface LeaderBoardService {
    /**
     * Retrieves the current leader board with the top score
     users
     * @return the users with the highest score
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
