/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Class for the register of a cashier
public class Register {
    
    // Class variables
    Sale currentSale;
    
    // Constructor
    public Register() {
        
    }
    
    // Create a new Transaction
    public void makeNewSale() {
        currentSale = new Sale();
    }
    
    // Enter an item into the sale
    public void enterItem(String barcode, int quantity) {
        currentSale.makeItem(barcode, quantity);
    }
    
    // Make a payment for the transaction and th
    public void makePayment(String type) {
        currentSale.makePayment(type);
    }
    
    // End the transaction without a payment
    public void endSale() {
        currentSale.becomeComplete();
       currentSale = null;
    }
    
    public boolean hasSale() {
        if (currentSale == null) {
            return false;
        }
        return true;
    }
}
