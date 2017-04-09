package Module09.inbuiltclasses.objectclass.clonning;

class IntegerStack {
  	int [] buffer;
  	int top;
  	IntegerStack (int size) {
    			buffer = new int [size];
    			top = -1;
  	}
  	void push (int item) {
   			 buffer [++top] = item;
  	}
  	int pop () {
    			return buffer [top--];
  	}
}
public class WithoutClonning {
	public static void main (String [] args) throws Exception {
    			IntegerStack first = new IntegerStack (5);
    			first.push (10);
    			first.push (20);
    			IntegerStack second = first;	
    			System.out.println ("first element:" + first.pop ());
    			first.push (99);
    			System.out.println ("second element:" + second.pop ());
    			System.out.println ("second element:" + second.pop ());
  	}
}

