
package ProcessSale;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

// GUI interface for the rental class
class RegisterGUI implements Observer {
    JFrame f;
    JPanel p;
    FlowLayout fl;
    JTextField itemsRentedTF;
    JButton createSale, pay, endSale, createRental, cancelSale;
    
    // Constructor to initially set up the GUI
    public RegisterGUI() {
        f = new JFrame("Register GUI");
        p = new JPanel();
        fl = new FlowLayout(FlowLayout.CENTER);
        
        itemsRentedTF = new JTextField("Register: \n");
        itemsRentedTF.setPreferredSize( new Dimension( 200, 100 ) );
        createSale = new JButton("New Sale");
        pay = new JButton("Pay");
        endSale = new JButton("Finish Sale");
        createRental = new JButton("New Rental");
        cancelSale = new JButton("Cancel Sale");
        
        p.setLayout(fl);
        p.add(itemsRentedTF);
        p.add(createSale);
        p.add(pay);
        p.add(endSale);
        p.add(createRental);
        p.add(cancelSale);
        
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }
    
    // Update the GUI
    public void update(Observable subject, Object subjectChange) { 
        if (subjectChange.equals("Sale")) {
            itemsRentedTF.setText("New Sale!"); 
        } 
        else if (subjectChange.equals("Pay")) {
            itemsRentedTF.setText("Payment Successful!"); 
        } 
        else if (subjectChange.equals("Finish")) {
            itemsRentedTF.setText("Completed The Sale!"); 
        } 
        else if (subjectChange.equals("Rental")) {
            itemsRentedTF.setText("New Rental!"); 
        } 
        else if (subjectChange.equals("Cancel Sale")) {
            itemsRentedTF.setText("Canceled The Sale"); 
        } 
        else {
            itemsRentedTF.setText("Items Rented: 2");    // + ((Integer) subjectChange).toString());
        }
    }

    // Add a controller for this view
    public void addController(ActionListener controller) { 
        createSale.addActionListener(controller); 
        pay.addActionListener(controller); 
        endSale.addActionListener(controller); 
        createRental.addActionListener(controller); 
        cancelSale.addActionListener(controller); 
    }

}
