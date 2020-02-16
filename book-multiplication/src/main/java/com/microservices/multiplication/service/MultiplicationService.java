package com.microservices.multiplication.service;

import com.microservices.multiplication.domain.Multiplication;
import com.microservices.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

/**
 * <p>
 *     业务层
 * </p>
 */
public interface MultiplicationService {

    /**
     * @return a multiplication of randomly generated numbers.
     */
    Multiplication createRandomMultiplication();

    /**
     * @param resultAttempt
     * @return true if the attempt matches the result of the multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    /**
     * Gets the statistics for a given user.
     *
     * @param userAlias the user's alias
     * @return a list of {@link MultiplicationResultAttempt} objects, being the past attempts of the user.
     */
    List<MultiplicationResultAttempt> getStatsForUser(final String userAlias);
}
