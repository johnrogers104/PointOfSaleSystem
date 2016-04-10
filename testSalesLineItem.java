package ProcessSale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tjb317
 */
public class testSalesLineItem {
    int quantity;
    ProductDescription desc;
    int price;
    SalesLineItem line;
    
    public testSalesLineItem(){
  
    }
 
    @Test(expected = NullPointerException.class)
    public void testgetPrice(){
        assertNotNull("Getting Price FAILED",line.getPrice());
    }
    
    @Test(expected = NullPointerException.class)
    public void testgetSubtotal(){
        assertEquals("Calculating subtotal FAILED",line.getSubtotal(), (quantity*price));
    }
 

    @Before
    public void setUp() {
     
    }
    
    @After
    public void tearDown() {
    }
}
