package Module11.collections.listinterfaces;

import java.util.*;

class FailFastBehaviour{
	public static void main(String[] args){	
		LinkedList ll = new LinkedList();
		ll.add(23); ll.add(546);ll.add(456);
		
		Iterator i = ll.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
			ll.add(34);
		}
		
		System.out.println(ll);				
	}
}

