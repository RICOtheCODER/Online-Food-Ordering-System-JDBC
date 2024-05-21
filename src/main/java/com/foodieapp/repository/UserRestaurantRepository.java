package com.foodieapp.repository;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface UserRestaurantRepository {

        public List<Restaurant> searchByLocation(String location) throws SQLException;

        public List<Restaurant> findRestaurantByName(String name);

         public List<Restaurant> findRestaurantByType(CuisineType type);

         public List<Restaurant> findAllActiveRestaurant();

         public List<Restaurant> findAllDeactivatedRestaurant();

}
