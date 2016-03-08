
package ProcessSale;

// Class to make a payment for a sale
public class Payment {
    
    // Class variables
    String type;
    double amount;
    
    // Constructor
    public Payment(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
    
    // Upload the payment to the database
    public boolean finalizePayment() {
        
        // Check the type of payment
        if (type.equalsIgnoreCase("cash")) {
            PersistentStorage.makePayment(amount);
        } else {
            try {
                int cardNum = Integer.parseInt(type);
                PersistentStorage.makePayment(type, amount);
                
            } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
            }
        }
        return true;
    }
    
    
}
