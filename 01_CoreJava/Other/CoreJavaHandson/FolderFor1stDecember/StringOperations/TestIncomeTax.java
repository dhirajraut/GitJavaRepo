
public class TestIncomeTax {

	public static void main(String[] args)
		{	int n = (int) IncomeTaxFunctions.getTotalIncomeTax(300000, 50000);	
			String str = ConvertNumWord.convert(n);
			System.out.println(str);
		}
}
