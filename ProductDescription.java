/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jane
 */
public class ProductDescription {
    
    //variables
    String description;
    int price; 
    int barcode;
    
    //constructor
    public ProductDescription(int barcode) {
        if (PersistentStorage.inDatabase(barcode)) {
            description = PersistentStorage.getProductDesc(barcode);
            price = PersistentStorage.getProductPrice(barcode);
        } else {
            description = "Error";
        }
    }
    
    public int getPrice(){
        return price;
    }
    
}
