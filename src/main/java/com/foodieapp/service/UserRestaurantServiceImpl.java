package com.foodieapp.service;
import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;
import com.foodieapp.repository.UserRestaurantRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserRestaurantServiceImpl implements UserRestaurantService{

    private final UserRestaurantRepository userRestaurantRepository;
    private final Scanner sc;

    public  UserRestaurantServiceImpl(UserRestaurantRepository userRestaurantRepository){
        this.userRestaurantRepository = userRestaurantRepository;
        this.sc = new Scanner(System.in);
    }

    private void displayRestaurants(List<Restaurant> restaurants) {
        System.out.println("Restaurants found:");
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }
    }

    public void findRestaurantByLocation(String location) throws SQLException {
        List<Restaurant> restaurants = userRestaurantRepository.searchByLocation(location);
        if (!restaurants.isEmpty()) {
            displayRestaurants(restaurants);
        } else {
            System.out.println("No restaurants found in the given location.");
        }
    }

    public void findRestaurantByName(String name) {
        List<Restaurant> restaurants = userRestaurantRepository.findRestaurantByName(name);
        if (!restaurants.isEmpty()) {
            displayRestaurants(restaurants);
        } else {
            System.out.println("No restaurants found with the given name.");
        }
    }

    public void findRestaurantByType(CuisineType type) {
        List<Restaurant> restaurants = userRestaurantRepository.findRestaurantByType(type);
        if (!restaurants.isEmpty()) {
            displayRestaurants(restaurants);
        } else {
            System.out.println("No restaurants found with the given cuisine type.");
        }
    }

    public List<Restaurant> findAllActiveRestaurant() {
        List<Restaurant> activeRestaurants = userRestaurantRepository.findAllActiveRestaurant();
        if (!activeRestaurants.isEmpty()) {
            displayRestaurants(activeRestaurants);
        } else {
            System.out.println("No active restaurants found.");
        }
        return activeRestaurants;
    }

    public List<Restaurant> findAllDeactivatedRestaurant() {
        List<Restaurant> inactiveRestaurants = userRestaurantRepository.findAllDeactivatedRestaurant();
        if (!inactiveRestaurants.isEmpty()) {
            displayRestaurants(inactiveRestaurants);
        } else {
            System.out.println("No inactive restaurants found.");
        }
        return inactiveRestaurants;
    }
}
