
package ProcessSale;

// Import 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        Payment payment = new Payment(this, type, id);
        payment.startRental();
        
        // Add items to the cart in the database
        for (SalesLineItem item: cart) {
            String barcode = Integer.toString(item.getBarcode());
            storage.updateInventory(barcode, item.getQuantity(), id);
        }
        
        return true; 
    }  
    
    // Finish a rental
    public boolean endRental(String type) {
        Payment payment = new Payment(this, type, id);
        payment.endRental();
        storage.closeConnection();
        return true; 
    }

}
