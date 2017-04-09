
public class DiscountCalculate {
	public float calcDiscount (float percentage, float billAmt) {
	 	return (billAmt * percentage)/100f;
	}

	public static void main(String[] args) {
		float billAmt = 2000;
		float discPerc = 10;
		DiscountCalculate bussCalc = new DiscountCalculate();
		float discount  =  bussCalc .calcDiscount (discPerc , billAmt );
		System.out.println ("Bill Amount:"+(billAmt+discount));
	}
}
