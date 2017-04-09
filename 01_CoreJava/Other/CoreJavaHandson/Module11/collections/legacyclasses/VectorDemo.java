package Module11.collections.legacyclasses;
import java.util.*;
public class VectorDemo {

	public static void main(String [ ] args){
			Vector v = new Vector(25,5);
			System.out.println("Initial Capacity : "+ v.capacity());

			for(int i =0;i<26;i++){
				v.add(i);
			}
			
			System.out.println("Capacity after adding components : "+ v.capacity());
			System.out.println(v);
			v.insertElementAt(30,23);
			System.out.println("After inserting element at position 23 : "+v);	
			System.out.println("Does vector contains 10 : " +v.contains(10)	);
			System.out.println("Element at position 26 : "+v.elementAt(26));
	
			Enumeration e;	
			for(e = v.elements();e.hasMoreElements();)
				System.out.print(" "+e.nextElement());
			System.out.println("Index of value =20 : "+v.indexOf(20));	
			System.out.println("Size of vector : "+v.size());
		}
}


					


