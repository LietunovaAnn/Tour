package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "CUSTOMERS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    private String name;
    @Email
    private String email;
    @Positive
    private int participationNumber;

    public Customer() {
    }
}
