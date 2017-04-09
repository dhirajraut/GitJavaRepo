import product.ProductA;

public class StatePattern {
	public static void main (String args[]) {
		ProductA product = new ProductA();
		while (null != product.getState()) {
			System.out.println("Product State = " + product.getState());
			product.promote();
		}
	}
}
