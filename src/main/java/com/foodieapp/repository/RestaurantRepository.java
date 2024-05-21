package com.foodieapp.repository;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Customer;
import com.foodieapp.model.MenuItem;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
        public void addRestaurant(Restaurant restaurant) throws SQLException;
        public void updatePrice(MenuItem menuItem,Restaurant restaurant,double newPrice,String name) throws SQLException;
        public void insertmenuItems(MenuItem menuItem,Restaurant restaurant) throws SQLException;

        public List<Restaurant> getAllRestaurants() throws SQLException; //left

        public Restaurant getRestaurantById(long restaurantId); //left


        public void updateRestaurant(Restaurant restaurant); //left

        void activateRestaurant(long restaurantID) throws SQLException;
        void deactivateRestaurant(long restaurantID) throws SQLException;

        public void addCustomer(Customer customer);


}
