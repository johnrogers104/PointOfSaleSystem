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
import javax.swing.JTextField;

// Class to act as a controler for the rental class
public class LoginController implements ActionListener {
    
    // private class variables
    private LoginNoGUI login;
    JTextField userName, passWord;
    
    // Constructor
    public LoginController() {
    
    }
    
    // Method to add a model to this class
    public void addLogin(LoginNoGUI login) {
        this.login = login;
    }
    
    // Override the action performed method
    public void actionPerformed(ActionEvent e) {
        // Perform different actions based on the event clicked
        if (e.getActionCommand().equals("Submit")) {
            login.isValidUser();
        }
    }
}