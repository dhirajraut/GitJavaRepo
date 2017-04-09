package Module08.exceptions.throwsclause;
import java.util.*;
public class ThrowsDemo {
	static float dividingNos(float i, float j)throws ArithmeticException{
		try{
			if(j==0)
				throw new ArithmeticException();
			else
				System.out.println("Printing value....");
			return i/j;
		}
		finally{
			System.out.println("This will be executed");
		}
	}
	public static void main(String[] args){
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a number : ");
			float i = sc.nextFloat();
			System.out.println("Enter another number : ");
			float j = sc.nextFloat();
			System.out.println(dividingNos(i,j));
		}
		catch(ArithmeticException ae){
			System.out.println("Enter number other than zero");
		}
		}
}
