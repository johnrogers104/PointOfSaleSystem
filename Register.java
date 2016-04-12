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
    
    public Register(Sale sale) {
        currentSale = sale;
    }
    
    // Create a new sale Transaction
    public void makeNewSale() {
        currentSale = new Sale();
        setChanged();
        notifyObservers("Sale");
        clearChanged();
    }
    
    // Create a new rental Transaction
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
    
    // Make a payment for the transaction 
    public void makePayment(String type) {
        if (currentSale != null) {
            currentSale.makePayment(type);
            endSale();
            setChanged();
            notifyObservers("Pay");
            clearChanged();
        } else {
            setChanged();
            notifyObservers("Can't Pay");
            clearChanged();
        }
    }
    
    // Cancel a sale
    public void cancelSale() {
        if (currentSale != null) {
            currentSale.cancelSale();
            currentSale = null;
            setChanged();
            notifyObservers("Cancel Sale");
            clearChanged();
        } else {
            setChanged();
            notifyObservers("Can't Cancel");
            clearChanged();
        }
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
    
    // Return an item
    public void returnItem(String barcode, int quantity) {
        currentSale.returnItem(barcode, quantity);
    }
}
