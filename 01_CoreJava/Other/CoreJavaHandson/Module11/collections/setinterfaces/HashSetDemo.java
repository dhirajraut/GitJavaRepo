package Module11.collections.setinterfaces;
import java.util.*;
class BankAccount{
	int accNo = 1001; 	String name = "abc";
//	public int hashCode(){   return accNo;  }
//	public boolean equals(Object o){return (this.hashCode() == o.hashCode());  }
}
class HashSetDemo{
   public static void main(String[] args){
	BankAccount ba1 = new BankAccount();
	BankAccount ba2 = new BankAccount();
	HashSet hs = new HashSet();
	hs.add(ba1); hs.add(ba2);hs.add(ba1);
	System.out.println("Size of HashSet = "+hs.size());
	if (hs.contains(ba1)){System.out.println("Hash Set contains ba1");}
	System.out.println("ba1 equals ba2 : "+ba1.equals(ba2));
	Iterator it = hs.iterator();
      while (it.hasNext()){System.out.println("HashCode : "+it.next().hashCode());}
	}
}	
