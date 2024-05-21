package com.foodieapp.repository;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface UserRestaurantRepository {

        public List<Restaurant> searchByLocation(String location) throws SQLException;

        public List<Restaurant> findRestaurantByName(String name) throws SQLException;

         public List<Restaurant> findRestaurantByType(CuisineType type) throws SQLException;

         public List<Restaurant> findAllActiveRestaurant() throws SQLException;

         public List<Restaurant> findAllDeactivatedRestaurant();

}
