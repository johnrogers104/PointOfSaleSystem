/*persistant storage class*/

public class PersistantStorage{
    private static PersistantStorage ps = null;
    private DataBaseConnection db = null;

    private PersistantStorage(){
	db = DataBaseConnection.getInstance();
    }

    public static PersistantStorage getInstance(){
	if(ps == null){
	    ps = new PersistantStorage();
	}
	else{
	    return ps;
	}
    }

    public boolean isUser(String user){
	return db.isInDataBase(user, "Users");
    }
    
    public boolean isInventory(String barcode){
	return db.isInDataBase(barcode, "Inventory");
    }

    public boolean checkPassword(String userID, String password){
	return db.verifyMatch(userID, password, "Users", "employee_id", "passwords");
    }

}