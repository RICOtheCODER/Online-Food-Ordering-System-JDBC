package com.foodieapp.repository;
import com.foodieapp.model.*;
import com.foodieapp.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRestaurantRepositoryImpl implements UserRestaurantRepository{

    private CentralRestaurantRepository centralRepository;

    public UserRestaurantRepositoryImpl() {
        this.centralRepository = CentralRestaurantRepository.getInstance();
    }

    public List<Restaurant> searchByLocation(String location) throws SQLException {
        try {
            Connection connection = JdbcConnectionUtil.getConnection();
            String query = "select * from restaurant where location like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location);
            ResultSet set = statement.executeQuery();
            return restaurantsFromResultset(set);
        }catch (SQLException e){
             throw new RuntimeException(e);
        }
    }

    public List<Restaurant> findRestaurantByName(String name) throws SQLException {
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="select * from restaurant where name like ?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,name);
        ResultSet set = statement.executeQuery();
        return restaurantsFromResultset(set);
    }

    public List<Restaurant> findRestaurantByType(CuisineType type) throws SQLException {
       Connection connection=JdbcConnectionUtil.getConnection();
       String query= "select * from restaurant where Cuisine_Type like ?";
       PreparedStatement statement= connection.prepareStatement(query);
       statement.setString(1,String.valueOf(type));
       ResultSet set= statement.executeQuery();
       return restaurantsFromResultset(set);
    }

    public List<Restaurant> findAllActiveRestaurant() throws SQLException {
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="select * from restaurant where isActive is true";
        PreparedStatement statement= connection.prepareStatement(query);
        ResultSet set= statement.executeQuery();
        return restaurantsFromResultset(set);
    }

    public List<Restaurant> findAllDeactivatedRestaurant() {
        try {
            Connection connection=JdbcConnectionUtil.getConnection();
            String query="select * from restaurant where isActive is false";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet set=statement.executeQuery();
            return restaurantsFromResultset(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private List<Restaurant> restaurantsFromResultset(ResultSet resultSet) throws SQLException {
         List<Restaurant> restaurantList=new ArrayList<>();
         while(resultSet.next()){
             String type=resultSet.getString("Cuisine_type");
             Restaurant restaurant;
             if(type.equalsIgnoreCase("FAST_FOOD_RESTAURANT")){
                 restaurant=new FastFoodRestaurant();
             } else if (type.equalsIgnoreCase("FINE_DINING_RESTAURANT")) {
                 restaurant=new FineDiningRestaurant();
             }
             else {
                 throw new IllegalArgumentException("Input is Not Matching");
             }
             restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
             restaurant.setName(resultSet.getString("Name"));
             restaurant.setCuisineType(CuisineType.valueOf(resultSet.getString("Cuisine_Type")));
             restaurant.setLocation(resultSet.getString("Location"));
             restaurant.setOpeningTime(LocalTime.parse(resultSet.getString("openingTime")));
             restaurant.setClosingTime(LocalTime.parse(resultSet.getString("closingTime")));
             restaurant.setActive(resultSet.getBoolean("isActive"));
             restaurant.setMenuItems(MenuItemsByID((int) restaurant.getRestaurantId()));
             restaurantList.add(restaurant);
         }
         return restaurantList;
    }
    private List<MenuItem> MenuItemsByID(int restaurant_id) throws SQLException {
        List<MenuItem> item=new ArrayList<>();
        MenuItem item1;
        ItemType type=null;
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="select Name,Price,menu_type,calorie_count from menuitems where restaurant_Id = ?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,restaurant_id);
        ResultSet res=statement.executeQuery();
        while (res.next()){
            String s=res.getString("Name");
            double price=res.getDouble("Price");
            String menutype=res.getString("menu_type");
            double calorie=res.getDouble("calorie_count");
            if("NON_VEG".equalsIgnoreCase(menutype)){
                type= ItemType.NON_VEG;
            } else if ("VEG".equalsIgnoreCase(menutype)) {
                type=ItemType.VEG;
            } else if ("VEGAN".equalsIgnoreCase(menutype)) {
                type=ItemType.VEGAN;
            }
            item1=new MenuItem(s,price,type,calorie);
            item.add(item1);
        }
        return item;
    }
}
