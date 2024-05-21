package com.foodieapp.repository;
import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRestaurantRepositoryImpl implements UserRestaurantRepository{

    private CentralRestaurantRepository centralRepository;

    public UserRestaurantRepositoryImpl() {
        this.centralRepository = CentralRestaurantRepository.getInstance();
    }

    public List<Restaurant> searchByLocation(String location) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : centralRepository.getRestaurants().values()) {
            if (restaurant.getLocation().equalsIgnoreCase(location)) {
                result.add(restaurant);
            }
        }
        return result;
    }

    public List<Restaurant> findRestaurantByName(String name) {
        return centralRepository.getRestaurants().values().stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantByType(CuisineType type) {
        return centralRepository.getRestaurants().values().stream()
                .filter(restaurant -> restaurant.getCuisineType() == type)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findAllActiveRestaurant() {
        return centralRepository.getRestaurants().values().stream()
                .filter(Restaurant::isActive)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findAllDeactivatedRestaurant() {
        return centralRepository.getRestaurants().values().stream()
                .filter(restaurant -> !restaurant.isActive())
                .collect(Collectors.toList());
    }
}
