package com.example.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Positive;

@Data
public class Tour {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "TOUR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    private String name;
    @Positive
    private int price;
    @Positive
    private int complexityId;

}
