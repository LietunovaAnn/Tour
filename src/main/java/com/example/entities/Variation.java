package com.example.entities;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class Variation {
    @Positive
    private int tourId;
    @Positive
    private int typeOfTourId;
}
