/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;

/**
 *
 * @author Jane
 */
public class ProductDescription {
    // Variables
    String description;
    int price; 
    int barcode;
    PersistentStorage storage;
    String productInfo;
    
    // Constructor
    public ProductDescription(String barcode) {
        try {
            this.barcode = Integer.parseInt(barcode);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        storage = PersistentStorage.getInstance();
        if (storage.isInventory(barcode)) {
            description = storage.getProductDesc(barcode);
            price = storage.getProductPrice(barcode);
            productInfo = storage.getProductInfo(barcode);
        } else {
            description = "Error";
        }
    }
    
    public int getPrice(){
        return price;
    }
    
    public String getDescription() {
     //productInfo = storage.getProductInfo(barcode);
     return productInfo;
    //return description;
    }
    
    public int getBarcode() {
        return barcode;
    }

}
