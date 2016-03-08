package ProcessSale;

import java.util.ArrayList;

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

    public boolean closeConnection(){
	return db.close();
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
        String query = "select name_of_product, quantity, color, size_of_shirt, unit_price from inventory where barcorde = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 5);
	inner = outer.get(0);
	String combined = null;
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i) + " ";
	}
	return combined;
    }
    
    public int getProductPrice(String barcode) {
        String query = "select unit_price from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
        outer = db.newQuery(query, 5);
        inner = outer.get(0);
	return Integer.parseInt(inner.get(0));
    }
    
    public boolean makePayment(String id, String type, double price, int quantity, String paymentType, String date, String due, String creditCardNum) {
        String query = "insert into transaction values('"+id+"','"+type+"',"+price+","+quantity+",'"+paymentType+"','"+date+"','"+due+"','"+creditCardNum+"')";
	return db.newUpdateQuery(query);
    }
}