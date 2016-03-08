
package ProcessSale;

// Class to make a payment for a sale
public class Payment {
    
    // Class variables
    String type;
    double amount;
    PersistentStorage storage;
    
    // Constructor
    public Payment(String type, double amount) {
        this.type = type;
        this.amount = amount;
        storage = PersistentStorage.getInstance();
    }
    
    // Upload the payment to the database
    public boolean finalizePayment() {
        
        // Check the type of payment
        if (type.equalsIgnoreCase("cash")) {
            storage.makePayment(type, amount);
        } else {
            try {
                int cardNum = Integer.parseInt(type);
                storage.makePayment(type, amount);
                
            } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
            }
        }
        return true;
    }
    
    
}
