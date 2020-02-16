package com.microservices.multiplication.repository;

import com.microservices.multiplication.domain.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * </p>
 */
@Repository
public interface MultiplicationRepository extends JpaRepository<Multiplication, Long>, JpaSpecificationExecutor<Multiplication> {

}