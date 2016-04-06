
package ProcessSale;

// Import 
import java.util.ArrayList;
import java.util.Date;

// Class to handle a rental
public class Rental extends Sale {

    // Class variables
    Date due;
    
    // Constructor
    public Rental() {
        due = new Date();
    }
    
    // Get a rental to be returned
    public void getRental(Date dateRented, String card) {
        // implement in persistent storage
    }
    
    // Return an item that was rented
    public boolean returnItem(String barcode, int qty) {
        return storage.returnItems(barcode, qty);
    }
    
    // Override payment method to handle a rental (may not need to override this - ask John Rogers)
    @Override
    public boolean makePayment(String type) {
        
        // Make sure payment is with a credit card
        try {
            Integer.parseInt(type);
        } catch (NumberFormatException e) {
            System.out.println("Error: not a credit card number");
            return false;
        }
        Payment payment = new Payment(this, type);
        payment.startRental();
        storage.closeConnection();
        return true; 
    }  
    
    // Finish a rental
    public boolean endRental(String type) {
        Payment payment = new Payment(this, type);
        payment.endRental();
        storage.closeConnection();
        return true; 
    }

}
