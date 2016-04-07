/* John Rogers
Tax Calculator Interface
*/


public class TaxInterface {
    private static TaxInterface ti = null;
    private TaxCalculator taxCalc;

    private TaxInterface(){
	taxCalc = new TaxCalculator();
    }

    public static TaxInterface getInstance(){
	if(ti == null){
	    ti = new TaxInterface();
	    return ti;
	}
	else
	    return ti;
    }

}