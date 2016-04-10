/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Import statements
import java.util.Observable;
import java.util.Observer;

// Class for the register of a cashier
public class Register extends Observable {
    
    // Class variables
    Sale currentSale;
    
    // Constructor
    public Register() {
        
    }
    
    // Create a new Transaction
    public void makeNewSale() {
        currentSale = new Sale();
        setChanged();
        notifyObservers("Sale");
        clearChanged();
    }
    
    // Create a new Transaction
    public void makeNewRental() {
        currentSale = new Rental();
        setChanged();
        notifyObservers("Rental");
        clearChanged();
    }
    
    // Enter an item into the sale
    public void enterItem(String barcode, int quantity) {
        currentSale.makeItem(barcode, quantity);
        setChanged();
        notifyObservers(currentSale.cart);
        clearChanged();
    }
    
    // Make a payment for the transaction and th
    public void makePayment(String type) {
        if (currentSale != null) {
            currentSale.makePayment(type);
            endSale();
            setChanged();
            notifyObservers("Pay");
            clearChanged();
        }
    }
    
    // Cancel a sale
    public void cancelSale() {
        currentSale = null;
        setChanged();
        notifyObservers("Cancel Sale");
        clearChanged();
    }
    
    // End the transaction without a payment
    public void endSale() {
        currentSale.becomeComplete();
        currentSale = null;
    }
    
    // Check if there is a current sale being processed
    public boolean hasSale() {
        if (currentSale == null) {
            return false;
        }
        return true;
    }
    
    // End a rental if the current sale is a rental
    public void endRental() {
        // Check if it is a rental
        if (currentSale instanceof Rental) {
            currentSale.becomeComplete();
            currentSale = null;
        } else {
            System.out.println("Error: No current rental to end");
        }
    }
}
