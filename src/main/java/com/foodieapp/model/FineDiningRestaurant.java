package com.foodieapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
@Data
@NoArgsConstructor
public class FineDiningRestaurant extends Restaurant{


    public FineDiningRestaurant(long restaurantId, String name, CuisineType cuisineType, String location, LocalTime openingTime, LocalTime closingTime, List<MenuItem> menuItems, boolean isActive) {
        super(restaurantId, name, cuisineType, location, openingTime, closingTime, menuItems, isActive);
    }

    @Override
    public String toString() {
        return "FineDiningRestaurant [" + super.toString() + "]";
    }
}
