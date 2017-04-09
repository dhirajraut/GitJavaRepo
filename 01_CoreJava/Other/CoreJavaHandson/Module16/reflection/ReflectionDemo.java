package Module16.reflection;

import java.lang.reflect.*; 

public class ReflectionDemo{
	public static void main (String [] args) {
		try {
			Class c = Class.forName ("Module04.inheritance.version01.BankAccount"); 
			Method mds [] = c.getDeclaredMethods (); 
			System.out.println("Method Summary : ");
			for (int i = 0; i < mds.length; i++)
				System.out.println (mds[i].toString ());
			
			Constructor ctor []= c.getConstructors();
			System.out.println("\nConstructor Summary : ");
			for (int i = 0; i < ctor.length; i++)
				System.out.println (ctor[i].toString ());
			
			Field flds[]= c.getFields();
			System.out.println("\nFields Summary : ");
			for (int i = 0; i < flds.length; i++)
				System.out.println (flds[i].toString ());
		}
		catch (Throwable e) {
			System.err.println (e);
		}
	}
} 
