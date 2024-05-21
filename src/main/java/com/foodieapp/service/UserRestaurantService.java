package com.foodieapp.service;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface UserRestaurantService {
    public void findRestaurantByLocation(String location) throws SQLException;

    public void findRestaurantByName(String name);
//
    public void findRestaurantByType(CuisineType type);

    public List<Restaurant> findAllActiveRestaurant();

    public List<Restaurant> findAllDeactivatedRestaurant();
}
