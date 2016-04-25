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
    @Override
    public void actionPerformed(ActionEvent e) {
        // Perform different actions based on the event clicked
        switch (e.getActionCommand()) {
            case "New Sale":
                register.makeNewSale();
                break;
            case "Pay":
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
                }   break;
            case "New Rental":
                register.makeNewRental();
                break;
            case "Cancel Sale":
                register.cancelSale();
                break;
            case "Add Item":
                {
                    String quest = JOptionPane.showInputDialog("Rental (r) or Sale(s)?");
                    String barcode = JOptionPane.showInputDialog("Enter an item barcode");
                    String qty = JOptionPane.showInputDialog("How many?");
                 
                    int intQty;
                    try {
                        intQty = Integer.parseInt(qty);
                        switch (quest) {
                            case "s":
                                register.enterItem(barcode, intQty, quest);
                                break;
                            case "r":
                                register.enterItem(barcode,intQty, quest);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"enter either r or s for rental or sale");
                                break;
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Error: Not an integer");
                        System.out.println(exception.getMessage());
                    }       break;
                }
            case "Return Item":
                {
                    String id = JOptionPane.showInputDialog("Enter transaction ID");
                    String barcode = JOptionPane.showInputDialog("Enter an item barcode");
                    String qty = JOptionPane.showInputDialog("How many are you returning?");
                    int intQty;
                    try {
                        intQty = Integer.parseInt(qty);
                        register.returnItem(id, barcode, intQty);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Error: No the correct input for a return");
                        System.out.println(exception.getMessage());
                    }       break;
                }
            default:
                break;
        }
    }
}