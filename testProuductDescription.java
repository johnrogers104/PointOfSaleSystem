/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessSale;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JGabbay
 */
public class testProuductDescription {
    PersistentStorage storage;
    String barcode;
     
    @Before
    public void setUp() {
        storage = PersistentStorage.getInstance();      
    }
    
    @After
    public void tearDown() {
        storage.closeConnection();
    }
     
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test(expected = IndexOutOfBoundsException.class)
    public void testProductDescription(){
     System.out.println("Testing Product Description\n");
     assertNotNull("Description Not Successful", storage.getProductDesc(barcode));
     //assertNotNull("Price Not Successful", storage.getProductPrice(barcode));
    }
   } 
