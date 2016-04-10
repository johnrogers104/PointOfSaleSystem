
package ProcessSale;

public class ManagingUsers {
    PersistentStorage storage;
    String role;    
    String employeeID;
    String password;
    private DataBaseConnection db = null;


    //Constructor
    ManagingUsers(String roleCM) {
        this.role = roleCM;
        this.storage = PersistentStorage.getInstance();
        db = DataBaseConnection.getInstance();

    }

    public String getRoles(String role, String password) {
        role = storage.getRole(role);
        if (storage.checkRolePermission(password, role) == true)  {   
            return role.toUpperCase();
        } else {
            return "Error in METHOD: getRoles";
        }
    }

    public void addUser(String newID, String newName, String newRole, String newPassword) {
      String query = "insert into users " + "values ('" + newID + "', '" + newName + "', '" + newRole + "', '" + newPassword + "')";
      db.newUpdateQuery(query);    
    }

    public void deleteUser(String password) {
        String query = "delete from users where passwords = '" + password + "'";
        db.newUpdateQuery(query);
    }
    
    

}

