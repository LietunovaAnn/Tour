package com.example.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Positive;


@Data
public class Discount {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "DISCOUNT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    @Positive
    private int participationNumber;
    @Positive
    private int percent;
}
