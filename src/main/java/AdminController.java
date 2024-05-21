import com.foodieapp.model.Customer;
import com.foodieapp.model.FastFoodRestaurant;
import com.foodieapp.model.FineDiningRestaurant;
import com.foodieapp.model.MenuItem;
import com.foodieapp.service.RestaurantService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {
    private final RestaurantService restaurantService;
    private Scanner sc;

    public AdminController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        this.sc = new Scanner(System.in);
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println("====Welcome to Admin====");
            System.out.println("1. Add New Fast Food Restaurant");
            System.out.println("2. Add New Fine Dining Restaurant");
            System.out.println("3. view ALl Restaurant");
            System.out.println("4. Update Menu Price");
            System.out.println("5. Activate Restaurant");
            System.out.println("6. Deactivate Restaurant");
            System.out.println("7. Add New Customer");
            System.out.println("8. Find Restaurant");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    restaurantService.addNewRestaurant(new FastFoodRestaurant());
                    break;
                case 2:
                    restaurantService.addNewRestaurant(new FineDiningRestaurant());
                    break;
                case 3:
                    restaurantService.getAllRestaurants();
                    break;
                case 4 :
                    System.out.println("====Menu Updation ====");
                    System.out.println("1.Fast Food ");
                    System.out.println("2.Fine Dining ");
                    int ch= sc.nextInt();
                    if(ch==1){
                        restaurantService.UpdateMenuItemPrice(new MenuItem(),new FastFoodRestaurant());
                    }
                    else if(ch ==2){
                        restaurantService.UpdateMenuItemPrice(new MenuItem(),new FineDiningRestaurant());
                    }
                    break;
                case 5:
                    System.out.print("Enter Restaurant ID to Activate: ");
                    long activateId = sc.nextLong();
                    restaurantService.activateRestaurant(activateId);
                    break;
                case 6:
                    System.out.print("Enter Restaurant ID to Deactivate: ");
                    long deactivateId = sc.nextLong();
                    restaurantService.deactivateRestaurant(deactivateId);
                    break;
                case 7:
                    restaurantService.addCustomer(new Customer());
                    break;
                case 8:
                    System.out.println("Enter Restaurant ID : ");
                    int id= sc.nextInt();
                    restaurantService.getRestaurantByID(id);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
