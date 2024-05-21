package com.foodieapp.repository;

import com.foodieapp.model.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class CentralRestaurantRepository {
    private static CentralRestaurantRepository instance;
    private Map<Long, Restaurant> restaurants;

    private CentralRestaurantRepository() {
        restaurants = new HashMap<>();
    }


    // returns the current instance ofthe classobject
    public static CentralRestaurantRepository getInstance() {
        if (instance == null) {
            instance = new CentralRestaurantRepository();
        }
        return instance;
    }

    public Map<Long, Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(long RestaurantID , Restaurant restaurant) {
        restaurants.put(RestaurantID, restaurant);
    }
}