package Module12.generics;

class Stack {
	int buffer[];
  	int size, top;
  	Stack(int s) {
			 size = s;
			 buffer = new int [size];
			 top=-1;
	}
  	void push(int item) { 
			buffer [ ++top ] = item;
	}
  	int pop() {
			 return buffer [ top-- ];
	}
}
public class StackTest {
  	public static void main (String args[]) {
    			Stack s = new Stack(3); 
    			s.push(10);
    			s.push(20);
    			s.push(30);
    			for(int i=0; i<s.size; i++) {
     				 System.out.println(s.pop());
    			}
  	}
}


