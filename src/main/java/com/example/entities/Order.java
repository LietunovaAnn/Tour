package com.example.entities;

import lombok.Data;

@Data
public class Order {
    private int id;
    private int tourId;
    private int customerId;
    private int discountPrise;

}
