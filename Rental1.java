/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author tjb317
 */
public class Rental1 {
       // Class variables
    String type;
    PersistentStorage storage;
    Sale finalSale;
   
    // Constructor
    public Rental1(Sale finalSale, String type) {
        this.type = type;
        this.finalSale = finalSale;
        storage = PersistentStorage.getInstance();
    }
    
    // Upload the rental to the database
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
        
        // Check to make sure rental is connected to credit card
        if (type.equalsIgnoreCase("cash")) {
            System.out.println("We do not accept cash for Rentals, please use a credit card");
            return false;
        } else {
            try {
                Integer.parseInt(type);
                for (SalesLineItem item: finalSale.cart) {
                    String id = Integer.toString( (int)(Math.random() * 1000 + 1) );
                    storage.makePayment(id, "Rent", item.getPrice(), item.quantity, "Card", date, returnDate, type);
                } 
            } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
            }
        }
        return true;
    }
    
    
}

