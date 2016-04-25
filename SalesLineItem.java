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
public class SalesLineItem {
    
    //variables
    int quantity;
    ProductDescription desc;
    //ProductDescription info;
 
    
    //constructor 
    //pass in the id/barcode of the current lineitem
    public SalesLineItem(String barcode, int qty){
        desc = new ProductDescription(barcode);
        quantity = qty;
        
    }
  
    //method to get the subtotal of the purchase after adding this current item
    //to the sale
    public double getSubtotal(){
        //take in id of saleslineitem from productdescription and return the 
        //price of that lineitem added to the current total
        double price = desc.getPrice();
        
        //Adjust the price based on the number of the same items
        double subTotal = price * quantity;
        return subTotal;
    }
    
   
   
   
    // Get the price of the item
    public double getPrice() {
        return desc.getPrice();
    }
    
    public String toString() {
       
        return "Barcode: " + getBarcode() + ", Qty: " + quantity + ", $" + desc.getPrice() + "\n" + 
                "Description: " + desc.getDescription();
   
        
    }
    
    public int getBarcode() {
        return desc.getBarcode();
    }
    
    public int getQuantity() {
        return quantity;
    }

}
