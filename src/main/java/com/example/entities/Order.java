package com.example.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
public class Order {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "ORDERS_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    private Integer id;
    private int tourId;
    private int customerId;
    private int discountPrise;

}
