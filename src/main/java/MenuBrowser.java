import com.foodieapp.model.CuisineType;
import com.foodieapp.service.UserRestaurantService;

import java.sql.SQLException;
import java.util.Scanner;

public class  MenuBrowser {
    private UserRestaurantService service;
    private Scanner sc;

    public MenuBrowser(UserRestaurantService service){
        this.service = service;
        sc = new Scanner(System.in);
    }

    public void browseMenus() throws SQLException {
        System.out.println("==== Browse Menus ====");
        System.out.println("1. Search By Location");
        System.out.println("2. Search By Cuisine Type");
        System.out.println("3. Search By Restaurant Name");
        System.out.println("4. Find All Active Restaurants ");
        System.out.println("5. Find All Deactivate Students ");
        System.out.println("6. Exit");
        System.out.print("Enter Your Choice: ");
        int choice = sc.nextInt();

        sc.nextLine();

        switch (choice) {
            case 1:
                searchByLocation();
                break;
            case 2:
                searchByCuisineType();
                break;
            case 3:
                searchByRestaurantName();
                break;
            case 4:
                findAllActivateRestaurants();
                break;
            case 5:
                findAllDeactivateRestaurants();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid Choice");
        }
    }
    private void searchByCuisineType() {
        System.out.print("Enter Cuisine Type (FAST_FOOD_RESTAURANT, FINE_DINING_RESTAURANT): ");
        String cuisineTypeString = sc.nextLine();
        try {
            CuisineType cuisineType = CuisineType.valueOf(cuisineTypeString.toUpperCase());
            service.findRestaurantByType(cuisineType);
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Invalid Cuisine Type");
        }
    }

    private void searchByLocation() throws SQLException {
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        service.findRestaurantByLocation(location);
    }

    private void searchByRestaurantName() {
        System.out.print("Enter Restaurant Name: ");
        String name = sc.nextLine();
        service.findRestaurantByName(name);
    }

    private void findAllActivateRestaurants() throws SQLException {
        service.findAllActiveRestaurant();
    }

    private void findAllDeactivateRestaurants(){
        service.findAllDeactivatedRestaurant();
    }

}
