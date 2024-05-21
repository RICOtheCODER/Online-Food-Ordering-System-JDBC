package com.foodieapp.repository;

import com.foodieapp.model.Customer;
import com.foodieapp.model.MenuItem;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public class RestaurantRepositoryJDBCimpls implements RestaurantRepository {
    @Override
    public void addRestaurant(Restaurant restaurant) {

    }

    @Override
    public void updatePrice(MenuItem menuItem, Restaurant restaurant, double newPrice, String name) throws SQLException {

    }



    @Override
    public void insertmenuItems(MenuItem menuItem, Restaurant restaurant) throws SQLException {

    }



    @Override
    public List<Restaurant> getAllRestaurants() {
        return null;
    }

    @Override
    public Restaurant getRestaurantById(long restaurantId) {
        return null;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

    }

    @Override
    public void activateRestaurant(long restaurantID) {

    }

    @Override
    public void deactivateRestaurant(long restaurantID) {

    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {

    }
}
