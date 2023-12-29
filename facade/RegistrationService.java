package facade;

import database.ProxyProtection;
import java.util.Scanner;
import models.Admin;
import models.Customer;
import models.User;

public class RegistrationService {
  CustomerMenuFacade customerMenu;
  AdminMenuFacade adminMenu;
  ProxyProtection db;
  String error;
  Scanner in;

  public RegistrationService() {
    this.in = new Scanner(System.in);
    this.db = new ProxyProtection();

    while (true) {
      System.out.println("===== LANDING PAGE =====");
      System.out.println("1. Login");
      System.out.println("2. Register");
      System.out.println("3. EXIT");
      System.out.print(">> ");

      int opt = in.nextInt();
      in.nextLine();

      switch (opt) {
        case 1:
          login();
          break;
        case 2:
          register();
          break;
        case 3:
          return;
        default:
          System.out.println("\n< Error: Invalid Option");
          cls();
          break;
      }
    }
  }

  public void login() {
    clear();
    while (true) {
      System.out.println("\n===== LOGIN MENU =====");
      System.out.print("Username: ");
      String username = in.nextLine();
      System.out.print("Password: ");
      String password = in.nextLine();
      User user = db.login(username, password);
      if (user == null) {
        System.out.printf("\n< Error: Invalid Credential");
        System.out.printf("\n< Press enter to continue or 0 to exit... ");

        String isExit = in.nextLine();

        if (isExit.equals("0")) {
          clear();
          return;
        }
        clear();
      } else {
        if (user.getIsAdmin()) {
          adminMenu = new AdminMenuFacade(user);
        } else
          customerMenu = new CustomerMenuFacade(user);
        return;
      }
    }
  }

  public void register() {
    while (true) {
      System.out.println("\n===== REGISTER FORM =====");

      System.out.print("Username: ");
      String username = in.nextLine();

      System.out.print("Password: ");
      String password = in.nextLine();

      char admin;

      do {
        System.out.print("Admin Privilege [T | F]: ");
        admin = in.next().charAt(0);
        if (admin == 't' || admin == 'f') {
          admin = Character.toUpperCase(admin);
        }
        in.nextLine();
      } while (admin != 'T' && admin != 'F');

      // validation register fields
      error = User.validate(username, password);
      if (error != null) {
        System.out.printf("\n< Invalid: %s\n", error);
        System.out.printf("< Press enter to continue or 0 to exit... ");
        String isExit = in.nextLine();

        if (isExit.equals("0")) {
          clear();
          return;
        }
        clear();

        continue;
      }

      User user = db.getOneUser(username);

      if (user == null) {
        if (admin == 'T') {
          user = new Admin(username, password);
          System.out.println("User created");
          db.addUser(user);
          adminMenu = new AdminMenuFacade(user);
        } else {
          user = new Customer(username, password);
          System.out.println("User created");
          db.addUser(user);
          customerMenu = new CustomerMenuFacade(user);
        }
        return;
      } else {
        System.out.println("User already exist");
      }
    }
  }

  public void clear() {
    System.out.print("\033[H\033[2J");
  }

  public void cls() {
    System.out.printf("< Press enter to continue...");
    in.nextLine();
    System.out.print("\033[H\033[2J");
  }
}
