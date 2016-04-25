/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

import java.util.ArrayList;
/**
 *
 * @author Jane
 */
public class PersistentStorage {
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
	    combined += inner.get(i) + " ";
	}
	return combined;
    }
    
    //gets the barcode and the unit price
     public String getProductDesc(String barcode) {
        String query = "select name_of_product, quantity, color, size_of_shirt, unit_price from inventory where barcode = '"+barcode+"'";
        //String query = "select name_of_product, color, size_of_shirt from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 5);
	inner = outer.get(0);
	String combined = "";
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i) + " ";
	}
	return combined;
    }
    
     //gets the name of the product, color, and size of the product
     public String getProductInfo(String barcode){
         String query = "select name_of_product, color, size_of_shirt from inventory where barcode = '"+barcode+"'";
         ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
         ArrayList<String> inner = new ArrayList<String>();
	outer = db.newQuery(query, 3);
	inner = outer.get(0);
	String combined = "";
	for(int i=0; i<inner.size(); i++){
	    combined += inner.get(i) + " ";
	}
	return combined;
     }
     
    public int getProductPrice(String barcode) {
        String query = "select unit_price from inventory where barcode = '"+barcode+"'";
	ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
        ArrayList<String> inner = new ArrayList<String>();
        outer = db.newQuery(query, 1);
        inner = outer.get(0);
	return Integer.parseInt(inner.get(0));
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
    
    public boolean returnItem(String barcode, int qty) {
        String query2 = "update inventory set quantity = quantity + "+qty+" where barcode = '"+barcode+"'";
        return db.newUpdateQuery(query2);
    }
    
    public boolean removeFromCart(String barcode, int qty, int id) {
        returnItem(barcode, qty);
        String query2 = "delete from cart where TRANSACTION_ID = " + id;
        return db.newUpdateQuery(query2);
    }
    
}
