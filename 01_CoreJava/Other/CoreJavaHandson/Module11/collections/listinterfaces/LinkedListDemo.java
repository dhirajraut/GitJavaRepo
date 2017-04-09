package Module11.collections.listinterfaces;

import java.util.*;
class LinkedListDemo{
	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		ll.add("PQR");ll.add(90);ll.add(68.98);ll.addFirst("AAA");
		ll.add(6.66);ll.addLast("ZZZ");
		System.out.println("The very First Object of linkedlist : "+ll.getFirst());
		System.out.println("The last object of linkedlist : "+ll.getLast());
		ListIterator lit = ll.listIterator();
		while (lit.hasNext()){System.out.println(lit.next());}
		System.out.println("Listing the elements in reverse order : ");
		while(lit.hasPrevious()){	System.out.println(lit.previous());}
		ll.removeFirst();
	  	System. out.println("Does list contain 'D' :"+ll.contains("D"));
		System.out.println("Elements remaining  after remove() method : "+ll); 
		System.out.println("Head of the list :using peek() method : "+ll.peek());
		System.out.println(ll);
		System.out.println("Get and remove the head of list:using poll()method :"+ ll.poll());
		System.out.println(ll);
	}
}	 
