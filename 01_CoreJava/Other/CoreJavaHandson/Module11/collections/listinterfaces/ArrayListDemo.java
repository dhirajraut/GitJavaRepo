package Module11.collections.listinterfaces;
import java.util.*;
class ArrayListDemo{
	  public static void main(String[] args){
	  ArrayList al = new ArrayList();
	  al.ensureCapacity(40);
	  System.out.println("Initial size of ArrayList:"+al.size());
	  al.add("C"); al.add(1, "D"); al.add(new Integer(15));al.add(new Float(3.14));
	  System.out.println("Size of ArrayList :"+al.size());
	  System.out.println("Contents after addition of ArrayList :"+al);
	  al.remove(2);
	  System.out.println("Contents after removal of ArrayList :"+al);
	  Iterator it = al.iterator();
	  while(it.hasNext()){ System.out.println("Contents :"+(Object)it.next()); }
	   Object[] arr = new Object[al.size()]; arr = al.toArray(arr);
	   System.out.println("Array contents :");
	   for(int i=0; i<arr.length; i++){System.out.println(arr[i]);}
	 }
	}
