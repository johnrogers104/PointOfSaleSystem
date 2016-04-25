
package ProcessSale;

// Import 



import java.util.ArrayList;



// Class to handle a rental
public class Rental extends Sale {
    
    
    ArrayList<RentLineItem> cart2;
    
    
    // Constructor
    public Rental() { 
     cart2 = new ArrayList<>();
     
    }
    
    @Override
    public void makeItem(String barcode, int qty) {
        cart2.add(new RentLineItem(barcode, qty) );
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
        cart2.stream().forEach((item) -> {
            String barcode = Integer.toString(item.getBarcode());        
            storage.updateInventory(barcode, item.getQuantity(), id);
        });
        return true; 
    }  
   
    @Override
   public void returnItem(String transactionId, String barcode, int quantity) {
        storage.returnItem(barcode, quantity);
        storage.updateDueDate(transactionId);
    }
    
    
  
}
