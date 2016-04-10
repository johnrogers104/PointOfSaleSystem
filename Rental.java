
package processrental;

// Import 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// Class to handle a rental
public class Rental extends Sale {

   
    
    // Constructor
    public Rental() {
    
    }
    
   
    public void getRental(Date dateReturned, String card){
 	// convert dateReturned to string, I forget how to do it, but call the string ‘date’
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String date = formatter.format(dateReturned);
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
        outer = storage.getRentalCart(date, card);
	for(int i=0; i<outer.size(); i++){
		inner = outer.get(i);
		String barcode = inner.get(0);
		int qty = Integer.parseInt(inner.get(1));
		storage.returnInventory(barcode, qty);
	}

}
    
    // Return an item that was rented
   /* public boolean returnItem(String barcode, int qty) {
        return storage.returnItems(barcode, qty);
    }*/
    
    
     // Upload the initial rental to the database
     public boolean endRental(String type) {  
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date dt = time;
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
                for (SalesLineItem item: cart) {
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
