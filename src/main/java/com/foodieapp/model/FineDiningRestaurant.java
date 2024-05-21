package com.foodieapp.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;
@Data
public class FineDiningRestaurant extends Restaurant{

    public FineDiningRestaurant(){

    }
    public FineDiningRestaurant(long restaurantId, String name, CuisineType cuisineType, String location, LocalTime openingTime, LocalTime closingTime, List<MenuItem> menuItems, boolean isActive) {
        super(restaurantId, name, cuisineType, location, openingTime, closingTime, menuItems, isActive);
    }
}
