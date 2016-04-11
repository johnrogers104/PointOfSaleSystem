package ProcessSale;

// Class to hold info for a product in the cart
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
    public int getSubtotal(){
        //take in id of saleslineitem from productdescription and return the 
        //price of that lineitem added to the current total
        int price = desc.getPrice();
        
        //Adjust the price based on the number of the same items
        int subTotal = price * quantity;
        return subTotal;
    }
    
    // Get the price of the item
    public double getPrice() {
        return desc.getPrice();
    }
    
    public String toString() {
        return "Barcode: " + getBarcode() + ", Qty: " + quantity + ", $" + desc.getPrice();
    }
    
    public int getBarcode() {
        return desc.getBarcode();
    }
    
    public int getQuantity() {
        return quantity;
    }
}
