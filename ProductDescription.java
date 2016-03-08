

// Class that contains info of a product
public class ProductDescription {
    
    // Variables
    String description;
    int price; 
    int barcode;
    
    // Constructor
    public ProductDescription(String barcode) {
        if (PersistentStorage.isInventory(barcode)) {
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
