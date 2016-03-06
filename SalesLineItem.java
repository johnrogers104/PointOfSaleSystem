

// Class to hold info for a product in the cart
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
