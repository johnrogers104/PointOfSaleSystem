/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Import statements
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Observable;


// Class for the register of a cashier
public class Register extends Observable {
    
    // Class variables
    Sale currentSale;
    Rental currentRental;
    // Constructor
    public Register() {
        
    }
    
    public Register(Sale sale, Rental rental) {
        currentSale = sale;
        currentRental = rental;
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
        currentRental = new Rental();
        setChanged();
        notifyObservers("Rental");
        clearChanged();
    }
    
    // Enter an item into the sale
    public void enterItem(String barcode, int quantity, String quest) {
        if(quest.equals("s")){
        currentSale.makeItem(barcode, quantity);
        setChanged();
        notifyObservers(currentSale.cart);
        clearChanged();
        }
        else if (quest.equals("r")){
            currentRental.makeItem(barcode, quantity);
            setChanged();
            notifyObservers(currentRental.cart2);
            clearChanged();
        }
        else{
            System.err.println("Quest isn't correct");
        }
        }
    
    // Make a payment for the transaction 
    public void makePayment(String type) {
        if (currentSale != null && currentRental == null) {
            currentSale.makePayment(type);
            endSale();
            setChanged();
            notifyObservers("Pay");
            clearChanged();
        } else if (currentRental != null){
            currentRental.makePayment(type);
            endSale();
            setChanged();
            notifyObservers("Pay");
            clearChanged();
        }else{
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
            currentRental.becomeComplete();
            currentRental = null;
    }
    
    // Return a Sales item
    public void returnItem(String transactionID, String barcode, int quantity) {
        currentSale.returnItem(transactionID, barcode, quantity);
    }
    
    //Renturn a Rental item
    public void returnRentalItem(String transactionID, String barcode, int quantity){
        currentRental.returnItem(transactionID, barcode, quantity);
    }
    
}
