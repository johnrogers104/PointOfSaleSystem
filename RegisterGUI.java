
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
    JTextField notificationTF;
    JTextArea itemsRentedTF;
    JButton createSale, createRental, pay, cancelSale, item, returnItem;
    
    // Constructor to initially set up the GUI
    public RegisterGUI() {
        f = new JFrame("Register GUI");
        p = new JPanel();
        fl = new FlowLayout();
        
        notificationTF = new JTextField("Register: \n");
        notificationTF.setPreferredSize( new Dimension( 150, 20 ) );
        itemsRentedTF = new JTextArea(10, 20);
        itemsRentedTF.setPreferredSize( new Dimension( 200, 100 ) );
        
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
        
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }
    
    // Update the GUI
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
        } 
        else if (subjectChange.equals("Can't Pay")) {
            notificationTF.setText("No Sale To Pay For!"); 
        } 
        else if (subjectChange.equals("Cancel Sale")) {
            notificationTF.setText("Canceled The Sale"); 
        } 
        else if (subjectChange.equals("Can't Cancel")) {
            notificationTF.setText("No Sale To Cancel!"); 
        } 
        else if (subjectChange instanceof ArrayList<?>) {
            notificationTF.setText("Added An Item!"); 
            ArrayList items = (ArrayList) subjectChange;
            itemsRentedTF.append(items.size() + ":   " + items.get(items.size()-1).toString() + "\n");
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
