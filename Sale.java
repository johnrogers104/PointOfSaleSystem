/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Imports
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;


// Class to perform a sale
public class Sale extends Observable {
    
    // Class variables
    boolean isComplete;
    Date time;
    ArrayList<SalesLineItem> cart;
    int id;
    PersistentStorage storage;

    // Constructor
    public Sale() {
        time = new Date();
        isComplete = false;
        cart = new ArrayList<>();
        id = (int)(Math.random() * 1000 + 1);
	storage = PersistentStorage.getInstance();
    }
    
    // Finalize the sale
    public void becomeComplete() {
        isComplete = true;
    }
    
    // Create a new item for the sale
    public void makeItem(String barcode, int qty) {
        cart.add( new SalesLineItem(barcode, qty) );
    }
    
    // Make a payment for this sale. Only 1 per sale
    public boolean makePayment(String type) {
        Payment payment = new Payment(this, type, id);
        payment.finalizePayment();
        
        // Add items to the cart in the database
        for (SalesLineItem item: cart) {
            String barcode = Integer.toString(item.getBarcode());
            storage.updateInventory(barcode, item.getQuantity(), id);
        }
        return true;
    }
    
    // Get the total amount for this sale (no tax)
    public double getTotal() {
        double total = 0.0;
        for (SalesLineItem item: cart) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    
    // Cancell this current sale
    public void cancelSale() {
        // Remove all objects
        cart = null;
        id = 0;
    }
    
    // Return an item from a sale by adding to inventory
    public void returnItem(String barcode, int quantity) {
        storage.returnItem(barcode, quantity);
    }
    
    
}
