package Module11.maps;
import java.util.*;

class maptest{
	int i =20;String s = "xyz";
	/*public boolean equals(Object o){return (this.hashCode()== o.hashCode());}
	public int hashCode(){return i;}	*/	
}
class HashMapTest {
  public static void main (String [] args) {
   Map m =new HashMap ();
   maptest a = new maptest();
   maptest a1 = new maptest();
   m.put(1,a);
   m.put(1,a1);
   m.put ("A","1");
   m.put ("C","2");
   m.put ("B","3");
   System.out.println(m);
   System.out.println ("\nm.size ()= " + m.size ());

   System.out.println ("Displaying keys: ");
   System.out.println ("\nDisplaying key-values: ");
Iterator it = m.entrySet ().iterator (); 
      while (it.hasNext ()) {
        Map.Entry me = (Map.Entry) it.next ();
        System.out.println (me.getKey () + " " + me.getValue ());
      }
      Set s = m.keySet ();
      it = s.iterator ();
      while (it.hasNext ()) {
      System.out.println (it.next ());
    }
    System.out.println ("\nDisplaying only values: ");
    it = m.values().iterator ();
    while (it.hasNext ()) 
      System.out.println (it.next ());
      }
  	
}

