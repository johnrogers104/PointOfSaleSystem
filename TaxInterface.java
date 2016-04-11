package ProcessSale;

import com.Tax.*;

/* John Rogers
Tax Calculator Interface
*/


public class TaxInterface {
    private static TaxCalculator taxCalc = TaxCalculator.getInstance();
    
    public static double getTax(double subtotal) {
	double tax = taxCalc.calculateTax(subtotal);
	return tax;
    }
}