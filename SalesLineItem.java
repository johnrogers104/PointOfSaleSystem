/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jane
 */
public class SalesLineItem {
    
    //variables
    int quantity;
    ProductDescription desc;
    
    //constructor 
    //pass in the id/barcode of the current lineitem
    public void SalesLineItem(String barcode){
        desc = new ProductDescription(barcode);
        
    }
      
    //method to get the subtotal of the purchase after adding this current item
    //to the sale
    public int getSubtotal(){
        //take in id of saleslineitem from productdescription and return the 
        //price of that lineitem added to the current total
        int price = desc.getPrice();
        
        //Adjust the price based on the number of the same items
        int subTotal = price * quantity;
        return subTotal;
        
        
    }
}
