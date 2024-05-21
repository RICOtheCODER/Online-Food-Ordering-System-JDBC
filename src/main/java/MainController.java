import com.foodieapp.repository.*;
import com.foodieapp.service.RestaurantService;
import com.foodieapp.service.RestaurantServiceImpl;
import com.foodieapp.service.UserRestaurantService;
import com.foodieapp.service.UserRestaurantServiceImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        RestaurantService restaurantService = new RestaurantServiceImpl(restaurantRepository);

        UserRestaurantRepository userRestaurantRepository = new UserRestaurantRepositoryImpl();
        UserRestaurantService userRestaurantService = new UserRestaurantServiceImpl(userRestaurantRepository);

        try {
            while (true) {
                System.out.println("====Welcome to Online Food Delivery System====");
                System.out.println("Select user Type");
                System.out.println("1.Admin");
                System.out.println("2.User");
                System.out.println("3.Exit");
                System.out.println("Enter Your Choice : ");
                int userType = sc.nextInt();
                switch (userType) {
                    case UserTypes.ADMIN:
                        AdminController adminController = new AdminController(restaurantService);
                        adminController.start();
                        break;
                    case UserTypes.USER:
                        UserController userController = new UserController();
                        userController.start();
                        break;
                    case UserTypes.EXIT:
                        return;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static class UserTypes {
        public static final int ADMIN = 1;
        public static final int USER = 2;
        public static final int EXIT = 3;
    }
}
