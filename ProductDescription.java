package ProcessSale;

// Class that contains info of a product
public class ProductDescription {
    
    // Variables
    String description;
    int price; 
    int barcode;
    PersistentStorage storage;
    
    // Constructor
    public ProductDescription(String barcode) {
        storage = PersistentStorage.getInstance();
        if (storage.isInventory(barcode)) {
            description = storage.getProductDesc(barcode);
            price = storage.getProductPrice(barcode);
        } else {
            description = "Error";
        }
    }
    
    public int getPrice(){
        return price;
    }
    
}
