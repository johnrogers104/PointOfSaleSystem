
package ProcessSale;

// Import 


import static ProcessSale.TaxInterface.getTax;
import java.util.ArrayList;
import java.util.Date;



// Class to handle a rental
public class Rental extends Sale {

    ArrayList<RentLineItem> cart2;
    
    
    // Constructor
    public Rental() { 

        cart2 = new ArrayList<>();

    }
    
    @Override
    public void makeItem(String barcode, int qty) {
        cart.add(new RentLineItem(barcode, qty) );
    }
    
 
    // Override payment method to handle a rental
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
        payment.finalizeRental();
      
        // Add items to the cart in the database
        for (RentLineItem item: cart2) {
            String barcode = Integer.toString(item.getBarcode());
            storage.updateInventory(barcode, item.getQuantity(), id);
        }
        return true; 
    }  
    
    
  
    
    //adds to the inventroy the amount of rental being return and sets due date to 0 so know rental is over
    //if there is a rental, 
    
  
}
