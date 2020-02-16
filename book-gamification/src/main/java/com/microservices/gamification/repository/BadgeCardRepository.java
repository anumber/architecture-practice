package com.microservices.gamification.repository;

import com.microservices.gamification.domain.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Handles data operations with BadgeCards
 */
public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long>, JpaSpecificationExecutor<BadgeCard> {

    /**
     * Retrieves all BadgeCards for a given user.
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, sorted by most recent.
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);

}
