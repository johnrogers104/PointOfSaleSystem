/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Login class
public class LoginNoGUI {
    
    PersistentStorage storage;
    String employeeID;
    String employeePassword;    
    
    // Constructor
    public LoginNoGUI(String id, String password) {
        employeeID = id;
        employeePassword = password;
        storage = PersistentStorage.getInstance();
    }
    
    // Check if the user is valid
    public boolean isValidUser() {
        boolean valid = false;
        if ( storage.isUser(employeeID) ) {
            valid = storage.checkPassword(employeeID,employeePassword);
        }
        return valid;
    }
     
}  
