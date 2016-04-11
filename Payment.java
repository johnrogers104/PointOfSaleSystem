package ProcessSale;

// Class to make a payment for a sale
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Payment {

    // Class variables
    String type;
    String transactionID;
    PersistentStorage storage;
    Sale finalSale;

    // Constructor
    public Payment(Sale finalSale, String type, int id) {
        this.type = type;
        transactionID = Integer.toString(id);
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
            int totalQuantity = 0;
            double totalPrice = 0;
            for (SalesLineItem item : finalSale.cart) {
                totalQuantity += item.getQuantity();
                totalPrice += item.getSubtotal();
            }
            storage.makePayment(transactionID, "Purch", totalPrice, totalQuantity, "Cash", date, "null", "null");
        } else {
            try {
                Integer.parseInt(type);
                int totalQuantity = 0;
                double totalPrice = 0;
                for (SalesLineItem item : finalSale.cart) {
                    totalQuantity += item.getQuantity();
                    totalPrice += item.getSubtotal();
                }
                storage.makePayment(transactionID, "Purch", totalPrice, totalQuantity, "Card", date, "null", type);
            } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
            }
        }
        return true;
    }

    // Begin a rental with the payment put in and update the database
    // May not need to implement
    public boolean startRental() {
        return finalizeRental();
    }
    
    // Upload the payment to the database
    public boolean finalizeRental() {
        
        // Variables used
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date dt = finalSale.time;
        String date = formatter.format(dt);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 30);
        dt = c.getTime();
        String returnDate = formatter.format(dt);
        
        try {
            Integer.parseInt(type);
            int totalQuantity = 0;
            double totalPrice = 0;
            for (SalesLineItem item : finalSale.cart) {
                totalQuantity += item.getQuantity();
                totalPrice += item.getSubtotal();
            }
            storage.makePayment(transactionID, "Rental", totalPrice, totalQuantity, "Card", date, returnDate, type);
        } catch (NumberFormatException e) {
            System.out.println("Error: not a credit card number");
            return false;
        }
        return true;
    }

    // End the rental and update the database
    public boolean endRental() {
        return false;
    }
}
