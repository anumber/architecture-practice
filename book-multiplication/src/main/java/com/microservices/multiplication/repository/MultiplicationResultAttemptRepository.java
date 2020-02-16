package com.microservices.multiplication.repository;

import com.microservices.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * </p>
 */
@Repository
public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt, Long>, JpaSpecificationExecutor<MultiplicationResultAttempt> {

    /**
     * @param userAlias
     * @return the latest 5 attempts for a given user, identified by their alias.
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}