package ProcessSale;

// Class to hold info for a product in the cart

import static ProcessSale.TaxInterface.getTax;

public class SalesLineItem {
    
    //variables
    int quantity;
    ProductDescription desc;
    
    //constructor 
    //pass in the id/barcode of the current lineitem
    public SalesLineItem(String barcode, int qty){
        desc = new ProductDescription(barcode);
        quantity = qty;
    }
      
    //method to get the subtotal of the purchase after adding this current item
    //to the sale
    public double getSubtotal(){
        return getPrice() * quantity;
    }
    
    // Get the Sale price of the item
    public double getPrice() {
        return desc.getSalePrice();
    }
    
   
    @Override
    public String toString(){
        return "Barcode: " + getBarcode() + ", Qty: " + quantity + ", $" + getSubtotal();
    }
    
    
    
    public int getBarcode() {
        return desc.getBarcode();
    }
    
    public int getQuantity() {
        return quantity;
    }
}
