/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Import methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

// Class to act as a controler for the rental class
public class RegisterController implements ActionListener {
    
    // private class variables
    private Register register;
    
    // Constructor
    public RegisterController() {
    }
    
    // Method to add a model to this class
    public void addRegister(Register register) {
        this.register = register;
    }
    
    // Override the action performed method
    public void actionPerformed(ActionEvent e) {
        // Perform different actions based on the event clicked
        if (e.getActionCommand().equals("New Sale")) {
            register.makeNewSale();
        } 
        else if (e.getActionCommand().equals("Pay")) {
            if (register.currentSale != null) {
                String card = JOptionPane.showInputDialog("Enter card number. Leave blank to pay with cash");
                try {
                    Integer.parseInt(card);
                    register.makePayment(card);
                } catch (Exception exception) {
                    register.makePayment("Cash");
                }
            } else {
                register.makePayment("Cash");
            }
        } 
        else if (e.getActionCommand().equals("New Rental")) {
            register.makeNewRental();
        } 
        else if (e.getActionCommand().equals("Cancel Sale")) {
            register.cancelSale();
        } 
        else if (e.getActionCommand().equals("Add Item")) {
            String barcode = JOptionPane.showInputDialog("Enter an item barcode");
            String qty = JOptionPane.showInputDialog("How many?");
            int intQty;
            try {
                intQty = Integer.parseInt(qty);
                register.enterItem(barcode, intQty);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Error: Not an integer");
                System.out.println(exception.getMessage());
            }
        }
    }
}