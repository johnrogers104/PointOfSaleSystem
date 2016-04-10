package ProcessSale;

import java.util.Scanner;

// Class to run the program
public class Main {

    public static void main(String[] args) {

        // Get username and password
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your employee ID and password");
        String username = input.nextLine();
        String password = input.nextLine();

        LoginNoGUI login = new LoginNoGUI(username, password);

        // Check validity
        if (!login.isValidUser()) {
            System.out.println("Please enter a valid username");
            System.exit(1);
        }

        System.out.println("Welcome " + username + "!");

        // Perform manager methods
        manager(input, username, password);
        
        // Set up initial register GUI and
        Register register = new Register();
        RegisterController regCont = new RegisterController();
        
        regCont.addRegister(register);
        
        RegisterGUI regGUI = new RegisterGUI();
        regGUI.addController(regCont);
        register.addObserver(regGUI);
        
        /*
        System.out.println("\nStarting up the register...");
        Register register = new Register();
        register.makeNewSale();

        String barcode = "";
        int qty = -1;
        while (!barcode.equalsIgnoreCase("q")) {
            System.out.println("Please enter an item barcode, or type q to quit");
            barcode = input.next();
            if (!barcode.equalsIgnoreCase("q")) {
                System.out.println("How many would you like?");
                qty = input.nextInt();
                register.enterItem(barcode, qty);
            }
        }
        if (register.hasSale()) {
            System.out.println("Please enter the credit card number or type c for cash...");
            String type = input.next();
            if (type.equalsIgnoreCase("c")) {
                type = "cash";
            }
            register.makePayment(type);
            register.endSale();
        }

        System.out.println("Thanks for coming. Bye!");
        */

    }

    public static void manager(Scanner input, String username, String password) {
        ManagingUsers userRole = new ManagingUsers(username);
        String role = userRole.getRoles(username, password);
        System.out.println("Role: " + role);

        boolean proceed = true;
        while (proceed == true) {
            if (role.trim().equals("MANAGER")) { //trim() removes extra whitespace
                
                System.out.println("Would you like to ~Add User~ ~Delete User~ ~Transaction~");
                String managerAnswer = input.nextLine().toUpperCase();
                
                if (managerAnswer.equals("ADD USER")) {
                    ManagingUsers addUser = new ManagingUsers(username);
                    System.out.println("What will be the User's new Employee_ID?");
                    String newID = input.nextLine();
                    System.out.println("What will be the User's new Employee Name?");
                    String newName = input.nextLine();
                    System.out.println("What will be the User's new Role?");
                    String newRole = input.nextLine();
                    System.out.println("What will be the User's new Password?");
                    String newPassword = input.nextLine();
                    
                    addUser.addUser(newID, newName, newRole, newPassword);
                    System.out.println("~Succesfully Added User~");
                } else if (managerAnswer.equals("DELETE USER")) {
                    ManagingUsers deleteUser = new ManagingUsers(username);
                    System.out.println("Enter the user's password for confirmation: ");
                    String deleteUserPassword = input.nextLine();
                    deleteUser.deleteUser(deleteUserPassword);
                    System.out.println("~Succesfully Deleted User~");
                } else if (managerAnswer.equals("TRANSACTION")) {
                    System.out.println("~Continue Transaction~");
                    proceed = false;
                } else {
                    System.out.println("Error in Manager Answer");
                }
            }
        }
    }
}
