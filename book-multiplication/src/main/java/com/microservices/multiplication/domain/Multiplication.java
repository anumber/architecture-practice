package com.microservices.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents a Multiplication (a * b).
 * <p>
 * </p>
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Multiplication {

    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    // example final field, final saves you a lot of potential problems when working with a multi-threaded system. visit https://en.wikipedia.org/wiki/Immutable_object.
    private final int factorA;
    private final int factorB;

    // Empty constructor for JSON (de)serialization
    public Multiplication() {
        this(0, 0);
    }
}