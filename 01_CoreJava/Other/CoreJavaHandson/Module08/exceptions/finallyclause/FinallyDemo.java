package Module08.exceptions.finallyclause;
import java.util.*;
public class FinallyDemo {
	static float dividingNos(float i, float j){
		try{
			if(j==0)
				throw new ArithmeticException();
			else
				System.out.println("Printing value....");
			return i/j;
		}
		catch(ArithmeticException ae){
			System.out.println("in catch");
			ae.printStackTrace();
		}
		finally{
			System.out.println("This will be executed");
		}
		return 0;
		}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number : ");
		float i = sc.nextFloat();
		System.out.println("Enter another number : ");
		float j = sc.nextFloat();
		System.out.println(dividingNos(i,j));
		
	}
}
