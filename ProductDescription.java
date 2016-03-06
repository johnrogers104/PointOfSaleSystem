

// Class that contains info of a product
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
