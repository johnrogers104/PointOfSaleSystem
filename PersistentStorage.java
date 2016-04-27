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
    
    public boolean isTransaction(String id) {
        return db.isInDataBase(id, "Transaction");
    }
     
    public boolean checkPassword(String userID, String password){
	return db.verifyMatch(userID, password, "Users", "employee_id", "passwords");
    }
    
    public boolean checkRolePermission(String password, String role) {
        return db.verifyMatch(password, role, "Users", "passwords", "role");
    }

    public String getRole(String employeeID) {
        String query = "select role from users where employee_id = '"+ employeeID+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 1);
	inner = outer.get(0);
	String combined = "";
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i) + "";
	}
	return combined;
    }
    /*
    public int getRentalDueDate(String){
        String query = "select DUE_DATE from transaction where barcode = '"+barcode+"'" ;
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
        outer = db.newQuery(query, 1);
        inner = outer.get(0);
	return Integer.parseInt(inner.get(0));
                 
    }*/
    
    public String getProductDesc(String barcode) {
        String query = "select name_of_product, quantity, color, size_of_shirt, unit_price from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 5);
	inner = outer.get(0);
	String combined = "";
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i) + "";
	}
	return combined;
    }
    
    public String getProductInfo(String barcode){
        String query = "select name_of_product, color, size_of_shirt from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 3);
	inner = outer.get(0);
	String combined = "";
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i);
	}
	return combined;
    
    }
    
    public int getSaleProductPrice(String barcode) {
        String query = "select unit_price from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
        outer = db.newQuery(query, 1);
        inner = outer.get(0);
	return Integer.parseInt(inner.get(0));
    }
    
    public int getRentProductPrice(String barcode){
        String query = "select RENTAL_PRICE from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
        outer = db.newQuery(query, 1);
        inner = outer.get(0);
	return Integer.parseInt(inner.get(0));
    }
    
    //returns the rental information as in its tansaction_Id, Total_Price, and DUe_Date
    public String getDueDate(String transactionID){
        String query = "select DUE_DATE from  TRANSACTION where TRANSACTION_ID ='"+transactionID+"'";
        ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 3);
	inner = outer.get(0);
	outer = db.newQuery(query, 1);
	inner = outer.get(0);
	return inner.get(0);
    }
    
    //returns transaction type
    public String getTransactionType (String transactionId){
        String query = "select TRANSACTION_TYPE from  TRANSACTION where TRANSACTION_ID ='"+transactionId+"'";
        ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 1);
	inner = outer.get(0);
	return inner.get(0);
    }
    
    
    
    public boolean makePayment(String id, String type, double price, int quantity, String paymentType, String date, String due, String creditCardNum) {
        String query = "insert into transaction values('"+id+"','"+type+"',"+price+","+quantity+",'"+paymentType+"','"+date+"','"+due+"','"+creditCardNum+"')";
	return db.newUpdateQuery(query);
    }

    public boolean updateInventory(String barcode, int qty, int id){
        String query1 = "update inventory set quantity = quantity - "+qty+" where barcode = '"+barcode+"'";
        String query2 = "insert into cart values ('" + id + "','" + barcode + "'," + qty + ")";
        db.newUpdateQuery(query1);
        return db.newUpdateQuery(query2);
        
    }
    
    public boolean updateDueDate(String transactionID){
        String query1 = "update transaction set DUE_DATE = '0' where transaction_ID = '"+transactionID+"'";
        return db.newUpdateQuery(query1);
    }
    
    public boolean returnRentedItem(String barcode, int qty) {
        String query2 = "update inventory set quantity = quantity + "+qty+" where barcode = '"+barcode+"'";
        return db.newUpdateQuery(query2);
    }

    public boolean returnSoldItem(String transactionID, String barcode, int qty) {
        String query2 = "insert into returnedinventory values(" + barcode + "," + qty + "," + transactionID + ")";
        return db.newUpdateQuery(query2);
    }


    
    //used to show rental is done
    public boolean zeroDueDate(String id){
        String query2 = "update tansaction set DUE_DATE = '0' where TRANSACTION_ID = '"+id+"'";
        return db.newUpdateQuery(query2);
    }
    
    
    public boolean removeFromCart(String barcode, int qty, int id) {
        returnRentedItem(barcode, qty);
        String query2 = "delete from cart where TRANSACTION_ID = " + id;
        return db.newUpdateQuery(query2);
    }
    
    

}