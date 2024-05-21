package com.foodieapp.service;

import com.foodieapp.model.*;
import com.foodieapp.repository.RestaurantRepository;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final Scanner sc;

    public  RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
        this.sc = new Scanner(System.in);
    }
    public void addNewRestaurant(Restaurant restaurant) throws SQLException {
        System.out.println("==== Add New Restaurant ====");
        System.out.print("Enter Restaurant Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Opening Time (HH:MM): ");
        LocalTime openingTime = LocalTime.parse(sc.nextLine());
        System.out.print("Enter Closing Time (HH:MM): ");
        LocalTime closingTime = LocalTime.parse(sc.nextLine());

        restaurant.setActive(true);

        restaurant.setName(name);
        restaurant.setLocation(location);
        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);

        if (restaurant instanceof FastFoodRestaurant) {
            ((FastFoodRestaurant) restaurant).setCuisineType(CuisineType.FAST_FOOD_RESTAURANT);
        } else if (restaurant instanceof FineDiningRestaurant) {
            ((FineDiningRestaurant) restaurant).setCuisineType(CuisineType.FINE_DINING_RESTAURANT);
        }
        restaurantRepository.addRestaurant(restaurant);
        System.out.println("Restaurant added successfully ");
        List<MenuItem> menuItems = new ArrayList<>();
        while (true) {
            System.out.println("Add Menu Item:");
            System.out.print("Name: ");
            String itemName = sc.nextLine();
            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Type (VEG, NON_VEG, VEGAN): ");
            ItemType itemType = ItemType.valueOf(sc.nextLine().toUpperCase());
            System.out.print("Calorie Count: ");
            double calorieCount = Double.parseDouble(sc.nextLine());

          //  menuItems.add(new MenuItem(itemName, price, itemType, calorieCount));
            MenuItem menuItem=new MenuItem(itemName,price,itemType,calorieCount);
            menuItems.add(menuItem);
            restaurantRepository.insertmenuItems(menuItem,restaurant);
            System.out.println("MenuItem Added");
            System.out.print("Add another item? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("No")) {
                break;
            }

        }

        restaurant.setMenuItems(menuItems);

        //restaurantRepository.addRestaurant(restaurant);
        //System.out.println("Restaurant added successfully!");
    }

    public void getAllRestaurants() throws SQLException {
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
        } else {
            System.out.println("=== All Restaurants ===");
            for (Restaurant restaurant : restaurants) {
                System.out.println(restaurant);
            }
        }
    }

    public void updateRestaurant(Restaurant updatedRestaurant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Restaurant ID to update: ");
        long restaurantId = sc.nextLong();
        sc.nextLine();

        Restaurant existingRestaurant = restaurantRepository.getRestaurantById(restaurantId);
        if (existingRestaurant == null) {
            System.out.println("Restaurant with ID " + restaurantId + " not found.");
            return;
        }

        if (updatedRestaurant.getRestaurantId() != existingRestaurant.getRestaurantId()) {
            System.out.println("Invalid restaurant ID. Please enter the correct ID.");
            return;
        }

        System.out.println("==== Update Restaurant ====");
        System.out.print("Enter Restaurant Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        System.out.print("Enter Opening Time (HH:MM): ");
        LocalTime openingTime = LocalTime.parse(sc.nextLine());
        System.out.print("Enter Closing Time (HH:MM): ");
        LocalTime closingTime = LocalTime.parse(sc.nextLine());

        existingRestaurant.setName(name);
        existingRestaurant.setLocation(location);
        existingRestaurant.setOpeningTime(openingTime);
        existingRestaurant.setClosingTime(closingTime);

        List<MenuItem> menuItems = existingRestaurant.getMenuItems();
        menuItems.clear();

        while (true) {
            System.out.println("Update Menu Item:");
            System.out.print("Name: ");
            String itemName = sc.nextLine();
            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Type (VEG, NON_VEG, VEGAN): ");
            ItemType itemType = ItemType.valueOf(sc.nextLine());
            System.out.print("Calorie Count: ");
            double calorieCount = Double.parseDouble(sc.nextLine());

            menuItems.add(new MenuItem(itemName, price, itemType, calorieCount));

            System.out.print("Update another item? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        existingRestaurant.setMenuItems(menuItems);

        restaurantRepository.updateRestaurant(existingRestaurant);
        System.out.println("Restaurant updated successfully!");
    }

    @Override
    public void UpdateMenuItemPrice(MenuItem menuItem, Restaurant restaurant) throws SQLException {
            System.out.println("Enter Restaurant : ");
            String name = sc.next()+sc.nextLine();
            System.out.println("Enter MenuItem Name : ");
            String menuName= sc.next()+sc.nextLine();
            System.out.println("Enter Price :");
            double p=sc.nextDouble();
            restaurant.setName(name);
            menuItem.setName(menuName);
            restaurantRepository.updatePrice(menuItem,restaurant,p,menuName);
            System.out.println("Menu Item Updated");
    }


    public void activateRestaurant(long restaurantID) throws SQLException {
        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantID);
//        if (restaurant == null) {
//            System.out.println("Restaurant with ID " + restaurantID + " not found.");
//            return;
//        }
//
//        if (restaurant.isActive()) {
//            System.out.println("Restaurant is already active.");
//            return;
//        }

       // restaurant.setActive(true);
        //restaurantRepository.updateRestaurant(restaurant);
        restaurantRepository.activateRestaurant(restaurantID);
        System.out.println("Restaurant activated successfully!");
    }


    public void deactivateRestaurant(long restaurantID) throws SQLException {

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantID);
//        if (restaurant == null) {
//            System.out.println("Restaurant with ID " + restaurantID + " not found.");
//            return;
//        }
//
//        if (!restaurant.isActive()) {
//            System.out.println("Restaurant is already inactive.");
//            return;
//        }

        //restaurant.setActive(false);
        //restaurantRepository.updateRestaurant(restaurant);
        restaurantRepository.deactivateRestaurant(restaurantID);
        System.out.println("Restaurant deactivated successfully!");
    }

}
