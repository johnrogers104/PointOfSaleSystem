
package ProcessSale;

// Class to make a payment for a sale

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Payment {
    
    // Class variables
    String type;
    PersistentStorage storage;
    Sale finalSale;
    
    // Constructor
    public Payment(Sale finalSale, String type) {
        this.type = type;
        this.finalSale = finalSale;
        storage = PersistentStorage.getInstance();
    }
    
    // Upload the payment to the database
    public boolean finalizePayment() {
        
        // Variables used
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String date = formatter.format(finalSale.time);
        
        // Check the type of payment
        if (type.equalsIgnoreCase("cash")) {
            
            for (SalesLineItem item: finalSale.cart) {
                String id = Integer.toString( (int)(Math.random() * 1000 + 1) );
                storage.makePayment(id, "Purch", item.getPrice(), item.quantity, "Cash", date, "null", "null");
            } 
        } else {
            try {
                Integer.parseInt(type);
                for (SalesLineItem item: finalSale.cart) {
                    String id = Integer.toString( (int)(Math.random() * 1000 + 1) );
                    storage.makePayment(id, "Purch", item.getPrice(), item.quantity, "Card", date, "null", type);
                } 
            } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
            }
        }
        return true;
    }
    
    
}
