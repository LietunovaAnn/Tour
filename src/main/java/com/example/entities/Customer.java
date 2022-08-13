package com.example.entities;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private int participationNumber;
}
