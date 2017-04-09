//import java.io.*;

class abc implements Cloneable
{ int x;
	abc(int y)
	{ x=y; }
	public String toString()
		{ return("Thanda matlab coca cola:"+x);	}
	public abc textX() throws CloneNotSupportedException
		{ abc ab=null;
		   //ab=(abc) super.clone();
		  //try{
			ab=(abc) super.clone();
	   	     //}
		  //catch (CloneNotSupportedException e)
			//{ System.out.println("Hi.");	}
		  return ab;
		}
}

public class CreatingClone
{ public static void main(String [] dubdum) throws CloneNotSupportedException, ArithmeticException
	{	abc y, x=new abc(10);
		y=x.textX();
		int a=7, b=0;
		try{System.out.println(a/b);}
		catch(RuntimeException e)
			{ System.out.println("Hi");	}
		System.out.println(y);
	}
}