/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

// Login class

import java.util.Observable;

public class LoginNoGUI extends Observable {
    
    PersistentStorage storage;
    String employeeID;
    String employeePassword;    
    
    // Constructor
    public LoginNoGUI() {
        storage = PersistentStorage.getInstance();
    }
    
    public LoginNoGUI(String id, String password) {
        employeeID = id;
        employeePassword = password;
        storage = PersistentStorage.getInstance();
    }
    
    public void newUser() {
        setChanged();
        notifyObservers(this);
        clearChanged();
    }
    
    public void setID(String id) {
        employeeID = id;
    }
    public void setPassword(String password) {
        employeePassword = password;
    }
    public String getID() {
        return employeeID;
    }
    
    // Check if the user is valid
    public boolean isValidUser() {
        boolean valid = false;
        if ( storage.isUser(employeeID) ) {
            return  storage.checkPassword(employeeID,employeePassword);
        }
	storage.closeConnection();
        return valid;
    }
     
}  
