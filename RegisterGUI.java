
package ProcessSale;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// GUI interface for the rental class
class RegisterGUI implements Observer {
    JFrame f;
    JPanel p;
    FlowLayout fl;
    JTextField notificationTF, subTotalLabel, grandTotalLabel, subTotal, grandTotal;
    JTextArea itemsRentedTF;
    JButton createSale, createRental, pay, cancelSale, item, returnItem;
<<<<<<< HEAD
    private double subtotal = 0;
    private double grandtotal = 0;

=======
    private double subtotal =0;
    private double grandtotal =0;
    
>>>>>>> origin/master
    // Constructor to initially set up the GUI
    public RegisterGUI() {
        f = new JFrame("Register GUI");
        p = new JPanel();
        fl = new FlowLayout();
        
        notificationTF = new JTextField("Register: \n");
        notificationTF.setPreferredSize( new Dimension( 150, 20 ) );
        itemsRentedTF = new JTextArea(10, 20);
        itemsRentedTF.setPreferredSize( new Dimension( 200, 100 ) );
	subTotalLabel = new JTextField("Subtotal: ");
        grandTotalLabel = new JTextField("Grand Total: ");
	subTotal = new JTextField(5);
	grandTotal = new JTextField(5);

        
        createSale = new JButton("New Sale");
        createRental = new JButton("New Rental");
        pay = new JButton("Pay");
        cancelSale = new JButton("Cancel Sale");
        item = new JButton("Add Item");
        returnItem = new JButton("Return Item");
        
        p.setLayout(fl);
        p.add(notificationTF);
        p.add(itemsRentedTF);
        p.add(createSale);
        p.add(createRental);
        p.add(pay);
        p.add(cancelSale);
        p.add(item);
        p.add(returnItem);
	p.add(subTotalLabel);
        p.add(subTotal);
        p.add(grandTotalLabel);
	p.add(grandTotal);
        
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }
    
    // Update the GUI
    @Override
    public void update(Observable subject, Object subjectChange) { 
        if (subjectChange.equals("Sale")) {
            notificationTF.setText("New Sale!");
            itemsRentedTF.setText("Items Sold: \n");
        } 
        else if (subjectChange.equals("Rental")) {
            notificationTF.setText("New Rental!"); 
            itemsRentedTF.setText("Items Rented: \n");
        } 
        else if (subjectChange.equals("Pay")) {
            notificationTF.setText("Payment Successful!"); 
	    grandtotal = 0;
            subtotal = 0;
	    subTotal.setText(Double.toString(subtotal));
            grandTotal.setText(Double.toString(grandtotal));
        } 
        else if (subjectChange.equals("Can't Pay")) {
            notificationTF.setText("No Sale To Pay For!"); 
        } 
        else if (subjectChange.equals("Cancel Sale")) {
            notificationTF.setText("Canceled The Sale");
	    grandtotal = 0;
	    subtotal = 0;
        } 
        else if (subjectChange.equals("Can't Cancel")) {
            notificationTF.setText("No Sale To Cancel!"); 
        } 
        else if (subjectChange instanceof ArrayList<?>) {
            notificationTF.setText("Added An Item!"); 
            ArrayList items = (ArrayList) subjectChange;
            itemsRentedTF.append(items.size() + ":   " + items.get(items.size()-1).toString() + "\n");
	    String[] tokens = (items.size() + ":   " + items.get(items.size()-1).toString()).split(" ");
            String priceWDollar = tokens[tokens.length -1];
            String price = "";
            for(int i=0; i<priceWDollar.length(); i++){
                if(priceWDollar.charAt(i) != '$'){
                    price += priceWDollar.charAt(i);
                }
            }
            double p = Double.parseDouble(price);
            subtotal += p;
            grandtotal = subtotal*1.07;
            
            String st = String.format("%.2f", subtotal);
            String gt = String.format("%.2f", grandtotal);
           
            subTotal.setText(st);
            grandTotal.setText(gt);

        } 
    }

    // Add a controller for this view
    public void addController(ActionListener controller) { 
        createSale.addActionListener(controller); 
        createRental.addActionListener(controller); 
        pay.addActionListener(controller);         
        cancelSale.addActionListener(controller);
        item.addActionListener(controller);
        returnItem.addActionListener(controller);
    }

}
