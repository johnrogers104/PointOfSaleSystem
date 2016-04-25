package ProcessSale;

// Class that contains info of a product
public class ProductDescription {
    
    // Variables
    String description;
    int salePrice; 
    int rentPrice;
    int barcode;
    String productInfo;
    PersistentStorage storage;
    
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
            salePrice = storage.getSaleProductPrice(barcode);
            productInfo = storage.getProductInfo(barcode);
            rentPrice = storage.getRentProductPrice(barcode);
        } else {
            description = "Error";
        }
    }
    
    public int getSalePrice(){
        return salePrice;
    }
    
    public int getRentPrice(){
        return rentPrice;
    }
    
    public String getDescription() {
        return productInfo;
    }
    
    public int getBarcode() {
        return barcode;
    }
    
}
