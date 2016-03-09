
package ProcessSale;

import java.util.Scanner;

// Class to run the program
public class Main {
    
    public static void main(String[] args) {
        
        // Get username and password
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter your username and password: ");
        String username = input.next();
        String password = input.next();
        LoginNoGUI login = new LoginNoGUI(username,password);
        
        // Check validity
        if (!login.isValidUser()) {
            System.out.println("Please enter a valid username");
            System.exit(1);
        }
        
        System.out.println("Welcome " + username + "!");
        System.out.println("Starting up the register...");
        Register register = new Register();
        register.makeNewSale();
        
        System.out.println("Please enter an item barcode, or type q to quit");
        String barcode = input.next();
        int qty;
        while (!barcode.equalsIgnoreCase("q")) {
            System.out.println("How many would you like?");
            qty = input.nextInt();
            register.enterItem(barcode, qty);
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
            
        System.exit(1);
        
    }
}
