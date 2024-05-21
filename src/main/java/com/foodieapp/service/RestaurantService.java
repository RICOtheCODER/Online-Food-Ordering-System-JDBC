package com.foodieapp.service;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.MenuItem;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantService {
    public void addNewRestaurant(Restaurant restaurant) throws SQLException;
    public void getAllRestaurants() throws SQLException;
    public void updateRestaurant(Restaurant restaurant);

    public void UpdateMenuItemPrice(MenuItem menuItem,Restaurant restaurant) throws SQLException;
    void activateRestaurant(long restaurantID) throws SQLException;
    void deactivateRestaurant(long restaurantID) throws SQLException;

}
