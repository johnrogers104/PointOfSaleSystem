/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;


/**
 *
 * @author tastiebrownies
 */
public class RentLineItem extends SalesLineItem{

    public RentLineItem(String barcode, int qty) {
        super(barcode, qty);
    }
    
    @Override
    public double getSubtotal(){
        return getPrice() * quantity;
    }
    
    //Gets the rent price
    @Override
    public double getPrice(){
        return desc.getRentPrice();
    }
    
    
    @Override
    public String toString(){
        return "Barcode: " + getBarcode() + ", Qty: " + quantity + ", $" + getSubtotal();
    }
    
}
