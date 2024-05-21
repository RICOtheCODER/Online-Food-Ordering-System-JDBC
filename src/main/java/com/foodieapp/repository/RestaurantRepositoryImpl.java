package com.foodieapp.repository;
import com.foodieapp.model.*;
import com.foodieapp.util.JdbcConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepository {

    private CentralRestaurantRepository centralRepository;

    public RestaurantRepositoryImpl() {
        this.centralRepository = CentralRestaurantRepository.getInstance();
        init();
    }

    void init() {
//        MenuItem item1 = new MenuItem("Biryani", 300, ItemType.NON_VEG, 2500);
//        Restaurant restaurant1 = new FastFoodRestaurant(Restaurant.getNextRestaurantId(), "Arsalan",
//                CuisineType.FAST_FOOD_RESTAURANT, "Mohali", LocalTime.of(10, 00),
//                LocalTime.of(21, 00), new ArrayList<>(List.of(item1)), true);
//        centralRepository.addRestaurant(restaurant1.getRestaurantId(),restaurant1);
//
//        MenuItem item2 = new MenuItem("Pizza", 250, ItemType.VEG, 1800);
//        MenuItem item3 = new MenuItem("Burger", 200, ItemType.VEG, 1500);
//
//        Restaurant restaurant2 = new FastFoodRestaurant(Restaurant.getNextRestaurantId(), "Pizza Hut",
//                CuisineType.FAST_FOOD_RESTAURANT, "Mohali", LocalTime.of(10, 30),
//                LocalTime.of(22, 00), new ArrayList<>(List.of(item2, item3)), true);
//        centralRepository.addRestaurant(restaurant2.getRestaurantId(),restaurant2);
    }

    public void addRestaurant(Restaurant restaurant) throws SQLException {
        Connection connection= JdbcConnectionUtil.getConnection();
        String query ="insert into restaurant(Name,Cuisine_Type,Location,openingTime,closingTime) values (?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,restaurant.getName());
        statement.setString(2, String.valueOf(restaurant.getCuisineType()));
        statement.setString(3,restaurant.getLocation());
        statement.setTime(4, Time.valueOf(restaurant.getOpeningTime()));
        statement.setTime(5, Time.valueOf(restaurant.getClosingTime()));
        int rowCount=statement.executeUpdate();
        if(rowCount==0){
           throw new RuntimeException("Failed to insert");
        }
        JdbcConnectionUtil.closeConnection();
    }

    @Override
    public void updatePrice(MenuItem menuItem, Restaurant restaurant,double newPrice,String name) throws SQLException {
    Connection connection=JdbcConnectionUtil.getConnection();
    String query = "update menuitems set price = ? where name =  ? and restaurant_id=?";
    PreparedStatement statement=connection.prepareStatement(query);
    int restaurant_id=getRestaurantIdByName(restaurant.getName());
    statement.setDouble(1,newPrice);
    statement.setString(2,name);
    statement.setInt(3,restaurant_id);
    int rowCount=statement.executeUpdate();
    JdbcConnectionUtil.closeConnection();
    }

    public void insertmenuItems(MenuItem menuItem,Restaurant restaurant) throws SQLException {
        Connection connection=JdbcConnectionUtil.getConnection();
        String query ="Insert into menuitems(name,price,menu_type,calorie_count,restaurant_id) values (?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        int restaurant_id=getRestaurantIdByName(restaurant.getName());
        statement.setString(1,menuItem.getName());
        statement.setDouble(2,menuItem.getPrice());
        statement.setString(3, String.valueOf(menuItem.getType()));
        statement.setDouble(4,menuItem.getCalorieCount());
        statement.setInt(5,restaurant_id);
        int rowCount= statement.executeUpdate();
        if(rowCount==0){
            throw new RuntimeException("Failed to Insert Item");
        }
        JdbcConnectionUtil.closeConnection();
    }

    private int getRestaurantIdByName(String resname) throws SQLException {
        String resno="";
        int val=0;
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="Select restaurant_Id from restaurant where name = ?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setString(1,resname);
        ResultSet resultSet=statement.executeQuery();
//        List<Restaurant> list=getRestaurantFromResult(resultSet);
//        JdbcConnectionUtil.closeConnection();
        while(resultSet.next()){
            val=resultSet.getInt("restaurant_id");
        }
        return val;
    }
    public List<Restaurant> getAllRestaurants() throws SQLException {
        Connection connection=JdbcConnectionUtil.getConnection();
        String query ="select * from restaurant";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        return getRestaurantFromResult(resultSet);
    }

    public Restaurant getRestaurantById(long restaurantId) throws SQLException {
        Connection connection=JdbcConnectionUtil.getConnection();
        String query = "select * from restaurant where restaurant_id= ?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,(int) restaurantId);
        ResultSet resultSet=statement.executeQuery();
        Restaurant rest1=null;
        while (resultSet.next()){
        String type = resultSet.getString("cuisine_Type"); // Assuming the column is named 'type'

        Restaurant restaurant;

        if ("FAST_FOOD_RESTAURANT".equalsIgnoreCase(type)) {
            restaurant = new FastFoodRestaurant();
        } else if ("FINE_DINING_RESTAURANT".equalsIgnoreCase(type)) {
            restaurant = new FineDiningRestaurant();
        } else {
            throw new IllegalArgumentException("Unknown restaurant type: " + type);
        }
            restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
            restaurant.setName(resultSet.getString("Name"));
            restaurant.setCuisineType(CuisineType.valueOf(resultSet.getString("Cuisine_Type")));
            restaurant.setLocation(resultSet.getString("Location"));
            restaurant.setOpeningTime(resultSet.getTime("openingTime").toLocalTime());
            restaurant.setClosingTime(resultSet.getTime("closingTime").toLocalTime());
            restaurant.setMenuItems(getMenuItemsByRestaurantId((int) restaurant.getRestaurantId()));
            restaurant.setActive(resultSet.getBoolean("isActive"));
            rest1=restaurant;
        }
        return rest1;
    }

    public void updateRestaurant(Restaurant restaurant) {
        centralRepository.getRestaurants().put(restaurant.getRestaurantId(), restaurant);
    }

    public void activateRestaurant(long restaurantID) throws SQLException {
//        Restaurant restaurant = centralRepository.getRestaurants().get(restaurantID);
//        if (restaurant != null) {
//            restaurant.setActive(true);
//            System.out.println("Restaurant activated successfully.");
//        } else {
//            System.out.println("Restaurant not found.");
//        }
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="update restaurant set isActive=true where restaurant_ID= ?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setInt(1, (int) restaurantID);
        int resultSet= statement.executeUpdate();
    }

    public void deactivateRestaurant(long restaurantID) throws SQLException {
//        Restaurant restaurant = centralRepository.getRestaurants().get(restaurantID);
//        if (restaurant != null) {
//            restaurant.setActive(false);
//            System.out.println("Restaurant deactivated successfully.");
//        } else {
//            System.out.println("Restaurant not found.");
//        }
        Connection connection=JdbcConnectionUtil.getConnection();
        String query= "update restaurant set isActive=false where restaurant_ID= ?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1, (int) restaurantID);
        int resultSet=statement.executeUpdate();
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
       Connection connection =JdbcConnectionUtil.getConnection();
       String query = "insert into customer(cust_name,cust_contactNo,cust_location)\n" +
               "values (?,?,?)";
       PreparedStatement statement=connection.prepareStatement(query);
       statement.setString(1, customer.getName());
       statement.setString(2, customer.getPhoneNo());
       statement.setString(3, customer.getLocation());
       int rowCount= statement.executeUpdate();
       if(rowCount==0){
           throw new RuntimeException("Customer Insertion Failed ");
       }
    JdbcConnectionUtil.closeConnection();
    }

//    private List<Restaurant> getRestaurantFromResult(ResultSet resultSet) throws SQLException {
//    List<Restaurant> restaurantList=new ArrayList<>();
//    Restaurant restaurant=null;
//    while (resultSet.next()){
//        restaurant=new FastFoodRestaurant();
//       restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
//       restaurant.setName(resultSet.getString("Name"));
//       restaurant.setCuisineType(CuisineType.valueOf(resultSet.getString("Cuisine_Type")));
//       restaurant.setLocation(resultSet.getString("Location"));
//       restaurant.setOpeningTime(resultSet.getTime("openingTime").toLocalTime());
//       restaurant.setClosingTime(resultSet.getTime("closingTime").toLocalTime());
//       restaurantList.add(restaurant);
//    }
//    return restaurantList;
//    }
    private List<Restaurant> getRestaurantFromResult(ResultSet resultSet) throws SQLException {
        List<Restaurant> restaurantList = new ArrayList<>();
        while (resultSet.next()) {
            // Get the type of restaurant from the result set
            String type = resultSet.getString("cuisine_Type"); // Assuming the column is named 'type'

            Restaurant restaurant;

            if ("FAST_FOOD_RESTAURANT".equalsIgnoreCase(type)) {
                restaurant = new FastFoodRestaurant();
            } else if ("FINE_DINING_RESTAURANT".equalsIgnoreCase(type)) {
                restaurant = new FineDiningRestaurant();
            } else {
                throw new IllegalArgumentException("Unknown restaurant type: " + type);
            }

            // Set the common properties of Restaurant
            restaurant.setRestaurantId(resultSet.getInt("restaurant_id"));
            restaurant.setName(resultSet.getString("Name"));
            restaurant.setCuisineType(CuisineType.valueOf(resultSet.getString("Cuisine_Type")));
            restaurant.setLocation(resultSet.getString("Location"));
            restaurant.setOpeningTime(resultSet.getTime("openingTime").toLocalTime());
            restaurant.setClosingTime(resultSet.getTime("closingTime").toLocalTime());
            restaurant.setMenuItems(getMenuItemsByRestaurantId((int) restaurant.getRestaurantId()));
            restaurant.setActive(resultSet.getBoolean("isActive"));

            restaurantList.add(restaurant);
        }

        return restaurantList;
    }
    private List<MenuItem> getMenuItemsByRestaurantId(int restaurantId) throws SQLException {
        List<MenuItem> item=new ArrayList<>();
        MenuItem item1;
        ItemType type=null;
        Connection connection=JdbcConnectionUtil.getConnection();
        String query="select Name,Price,menu_type,calorie_count from menuitems where restaurant_Id = ?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setInt(1,restaurantId);
        ResultSet res=statement.executeQuery();
        while (res.next()){
            String s=res.getString("Name");
            double price=res.getDouble("Price");
            String menutype=res.getString("menu_type");
            double calorie=res.getDouble("calorie_count");
            if(menutype.equalsIgnoreCase("NON_VEG")){
                type=ItemType.NON_VEG;
            } else if (menutype.equalsIgnoreCase("VEG")) {
                type=ItemType.VEG;
            } else if (menutype.equalsIgnoreCase("VEGAN")) {
                type=ItemType.VEGAN;
            }
            item1=new MenuItem(s,price,type,calorie);
            item.add(item1);
        }
        return item;
    }

}
