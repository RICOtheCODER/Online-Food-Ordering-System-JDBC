package com.foodieapp.repository;

import com.foodieapp.model.CuisineType;
import com.foodieapp.model.Restaurant;

import java.util.List;

public interface UserRestaurantRepository {

        public List<Restaurant> searchByLocation(String location);

        public List<Restaurant> findRestaurantByName(String name);

         public List<Restaurant> findRestaurantByType(CuisineType type);

         public List<Restaurant> findAllActiveRestaurant();

         public List<Restaurant> findAllDeactivatedRestaurant();

}
