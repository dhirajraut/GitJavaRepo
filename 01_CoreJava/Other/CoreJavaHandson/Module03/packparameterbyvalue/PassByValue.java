package Module03.packparameterbyvalue;

import Module03.packbankaccount.version02.BankAccount;

public class PassByValue {

	public static void main(String[] args) {
		int x = 15;
		int[] y = {10, 20, 30, 40};
		BankAccount ba = new BankAccount(101, "abc", 5000);
		
		System.out.println("Values before passing the parameters : ");
		System.out.print("X : "+x+"\tName : "+ba.getName()+"\tArray object : ");
		for(int z:y) {
			System.out.print(z+",");
		}
		System.out.println("\n");
	
// Calling a method to change the values.		
		changeThem(25, y, ba);
		
		System.out.println("Values after passing the parameters : ");
		
		System.out.print("X : "+x+"\tName : "+ba.getName()+"\tArray object : ");
		for(int z:y) {
			System.out.print(z+",");
		}
	}

	public static void changeThem(int x, int[] yc, BankAccount ba) {
		x = 60;
		for(int i=0; i<yc.length; i++) {
			yc[i]++;
		ba.setName("xyz");
		}
	}
}
