package ProcessSale;

/*persistant storage class*/
public class PersistentStorage{
    private static PersistentStorage ps = null;
    private DataBaseConnection db = null;

    private PersistentStorage(){
	db = DataBaseConnection.getInstance();
    }

    public static PersistentStorage getInstance(){
	if(ps == null){
	    ps = new PersistentStorage();
	}
	else{
	    return ps;
	}
        return ps;
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

    public String getProductDesc(String barcode) {
        return "not implemented";
    }
    
    public int getProductPrice(String barcode) {
        return -1;
    }
    
    public boolean makePayment(String type, double amount) {
        return false;
    }
}