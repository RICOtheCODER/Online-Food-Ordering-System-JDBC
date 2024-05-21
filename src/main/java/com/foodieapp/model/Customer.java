package com.foodieapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private String name;
    private String phoneNo;
    private String location;

    public Customer(String name, String phoneNo, String location) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.location = location;
    }
}
