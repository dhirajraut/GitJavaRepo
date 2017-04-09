package Module09.inbuiltclasses.objectclass.clonning;
class IntegerStack1 implements Cloneable {
	int buffer [];
 	int top;
  	IntegerStack1 (int size) {
   			buffer = new int [ size ];
    			top = -1;
  	}
	void push (int item) {   buffer [ ++top ] = item;  }
	int pop () {  return buffer [top-- ];  }
	
	public Object clone () throws CloneNotSupportedException {
    			IntegerStack1 temp = (IntegerStack1) super.clone ();
    			temp.buffer = (int []) buffer.clone ();
    			return temp;
  	}
}


	public class ClonningTest {
		public static void main(String [] args) throws Exception {
				IntegerStack1 first = new IntegerStack1 (5);
	    			first.push (10);
	    			first.push (20);
	    			IntegerStack1 second = (IntegerStack1) first.clone ();
	    			System.out.println ("first element:" + first.pop ());
	    			first.push (99);
	    			System.out.println ("second element:" + second.pop ());
	    			System.out.println ("second element:" + second.pop ());
	  	}
	}


