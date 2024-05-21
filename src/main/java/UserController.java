import com.foodieapp.repository.*;
import com.foodieapp.service.*;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    Scanner sc = new Scanner(System.in);
    private MenuBrowser browser;

    public UserController() {
        UserRestaurantRepository userRestaurantRepository = new UserRestaurantRepositoryImpl();
        UserRestaurantService userRestaurantService = new UserRestaurantServiceImpl(userRestaurantRepository);
        browser = new MenuBrowser(userRestaurantService);
    }

    public void start() throws SQLException {
        while (true) {
            System.out.println("====Welcome to User====");
            System.out.println("1.Browse Menus");
            System.out.println("2.Add Menu's to Cart");
            System.out.println("3.Exit");
            System.out.println("Enter your Choice");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    browser.browseMenus();
                    break;
                case 2:
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}
