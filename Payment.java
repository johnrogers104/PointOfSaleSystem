package ProcessSale;

// Class to make a payment for a sale
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment {

    // Class variables
    String type;
    String transactionID;
    PersistentStorage storage;
    Sale finalSale;
    Rental finalRental;

    
  

    // Constructor
    public Payment(Sale finalSale, Rental finalRental, String type, int id) {
        this.type = type;
        transactionID = Integer.toString(id);
        this.finalSale = finalSale;
        this.finalRental = finalRental;
        storage = PersistentStorage.getInstance();
    }

    // Upload the payment to the database
    public boolean finalizePayment() {
        // Variables used
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String date = formatter.format(finalSale.time);
        int totalQuantity = 0;
        double totalPrice = 0.00;
        double totalSale;
        double tax;
        String newline = System.getProperty("line.separator");
        String cart = "";
        String quest = "";
        String returnDate="";
        
        // Check the type of payment
        if (type.equalsIgnoreCase("cash")) {
            for (SalesLineItem item : finalSale.cart) {
                totalQuantity += item.getQuantity();
                totalPrice += item.getSubtotal();
                cart += item.toString() + newline;
            }
            tax=TaxInterface.getTax(totalPrice);
            totalSale = totalPrice + tax;
            storage.makePayment(transactionID, "Purch", totalSale, totalQuantity, "Cash", date, "null", "null");    
            } else {
                 try {
                Integer.parseInt(type);
                for (SalesLineItem item : finalSale.cart) {
                    totalQuantity += item.getQuantity();
                    totalPrice += item.getSubtotal();
                    cart += item.toString() + newline;
                      }
                tax=Math.floor(TaxInterface.getTax(totalPrice) *100)/100;
                totalSale = tax + totalPrice;
                storage.makePayment(transactionID, "Purch", totalSale, totalQuantity, "Card", date, "null", type);
                } catch (NumberFormatException e) {
                System.out.println("Error: not a credit card number");
                return false;
                }
              }
            try {
                    receipt(cart,totalPrice,totalSale,tax,quest,returnDate);
                  } catch (IOException ex) {
                   Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
                  } 
        return true;
    }

    
    
    // Upload the payment to the database
    public boolean finalizeRental() {
        // Variables used
        DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date dt = finalRental.time;
        String date = formatter.format(dt);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 30);
        dt = c.getTime();
        String returnDate = formatter.format(dt);
        String newline = System.getProperty("line.separator");
        String cart = "";
        int totalQuantity = 0;
        double totalSale;
        double tax;
        double totalPrice = 0.00;
        String quest ="r";
        
        
        try {
            Integer.parseInt(type);
            for (RentLineItem item : finalRental.cart2) {
                totalQuantity += item.getQuantity();
                totalPrice += item.getSubtotal();
                cart += item.toString()+newline;
            }
            storage.makePayment(transactionID, "Rental", totalPrice, totalQuantity, "Card", date, returnDate, type);
            tax=Math.floor(TaxInterface.getTax(totalPrice) *100)/100;
            totalSale = totalPrice + TaxInterface.getTax(totalPrice);
        } catch (NumberFormatException e) {
            System.out.println("Error: not a credit card number");
            return false;
        }
        try {
                receipt(cart,totalPrice,totalSale,tax,quest,returnDate);
            } catch (IOException ex) {
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            }
        return true; 
    }
    
    public void receipt(String cart, double totalPrice, double totalSale, double tax, String quest,String returnDate) throws IOException{ 
           PrintWriter writer = new PrintWriter("receipt.txt", "UTF-8");
        
            writer.println("Transaction Number: "+transactionID);
            writer.println("");
            writer.printf(cart);
            writer.println("");
            writer.println("-----------------------");
            writer.println("Subtotal: $"+totalPrice);
           
            writer.println("Tax: $"+tax);
            writer.println("-----------------------");
            writer.println("TOTAL: $"+totalSale);
             if(quest.equals("r")){
                 String day = returnDate.substring(0,2);
                 String month = returnDate.substring(2,4);
                 String year = returnDate.substring(4,8);
                 String returnForm = month+"/"+day+"/"+year;
                 writer.println("");
                 writer.println("RENTAL RULES:");
                 writer.println("THE RENTAL NEEDS TO BE RETURNED WITHIN 30 DAYS");
                 writer.println("OF THE INITIAL TRANSACTION TO BE CONSIDERED ON TIME");
                 writer.println("THE DUE DATE WILL BE: " + returnForm);
            }
            
            writer.close();
          } 
      }

    

