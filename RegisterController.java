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
            register.makeNewRental();
        } else if (e.getActionCommand().equals("Pay")) {
            register.makePayment("Cash");
        } else if (e.getActionCommand().equals("Finish Sale")) {
            register.endSale();
        }
    }
}