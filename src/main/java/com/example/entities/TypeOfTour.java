package com.example.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@SequenceGenerator(name = "seq", sequenceName = "TYPE_OF_TOUR_SEQ")
public class TypeOfTour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    private String name;
}
