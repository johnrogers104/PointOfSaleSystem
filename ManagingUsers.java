
package ProcessSale;

import java.util.Observable;

public class ManagingUsers extends Observable {
    PersistentStorage storage;
    String role;    
    String employeeID;
    String password;
    private DataBaseConnection db = null;


    //Constructor
    ManagingUsers() {
        this.storage = PersistentStorage.getInstance();
        db = DataBaseConnection.getInstance();
    }

    /*
    public String getRoles(String role, String password) {
        role = storage.getRole(role);
        if (storage.checkRolePermission(password, role) == true)  {   
            return role.toUpperCase();
        } else {
            return "Error in METHOD: getRoles";
        }
    }
    */

    public void addUser(String newID, String newName, String newRole, String newPassword) {
      String query = "insert into users " + "values ('" + newID + "', '" + newName + "', '" + newRole + "', '" + newPassword + "')";
      db.newUpdateQuery(query);    
    }

    public void deleteUser(String id) {
        String query = "delete from users where employee_id = '" + id + "'";
        db.newUpdateQuery(query);
    }
    
    public void startRegister() {
        setChanged();
        notifyObservers("Start");
        clearChanged();
    }
    
    

}

