package Module12.generics;
import java.util.*;
class GenericStack<A> {
	ArrayList<A> buffer;
  	int size, top;
  	GenericStack(int s) { 
    			size = s;
    			buffer = new ArrayList<A>(size);
    			top=-1; 
  	}
  	void addNewAccount(A item) { 
    			buffer.add(++top,item);
  	}
  	A getAccount() { 
    			return buffer.get(top--); 
  	}
}



