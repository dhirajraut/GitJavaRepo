import java.util.*;

//IMPORTANT : A linked list can be traversed through Iterator
//IMPORTANT : A linked list can be traversed through List Iterator in both directions

public class TestLinkedListClass
	{	public static void main(String[] args)
			{	LinkedList l1 = new LinkedList();
				
				// Inserting elements into Linked List
				l1.addFirst("B");				// Adds element as a first
				l1.add("F");					// Adds element at last in the List
				l1.add(new Float(3.14));
				l1.addLast(new Integer(15));	// Adds element in the last
				l1.addFirst("A");
				l1.add(3, "Z");					// Inserts element at 2nd position counting first as 0
				System.out.println("Size of a Linked List after addition :"+l1.size());
				System.out.println("Linked List after addition :"+l1);	// [A B F Z 3.14 15]
				
				// Removing elements into Linked List
				l1.removeFirst();
				l1.removeLast();
				l1.remove(new Float(3.14));
				System.out.println("Linked List after removal :"+l1);	// [B F Z]
				
				// Getting values
				System.out.println("The very first object:"+l1.getFirst());
				System.out.println("The very last object:"+l1.getLast());
				for(int i=0; i<l1.size(); i++)
					System.out.println("The "+i+"th element is :"+l1.get(i));
				
				// Setting values
				for(int i=0; i<l1.size(); i++)
					l1.set(i, (String)l1.get(i)+"Changed");
				System.out.println(l1);
				
				Iterator l1Itr = l1.iterator();
				System.out.println("Linked List through Iterator :");
				while(l1Itr.hasNext())
					System.out.println(l1Itr.next()+",");
				
				ListIterator l1LItr = l1.listIterator();
				System.out.println("Linked List through List Iterator in Forward order :");
				while(l1LItr.hasNext())
					System.out.println("Linked List through Iterator"+l1LItr.next());
				
				System.out.println("Linked List through List Iterator in Backward order :");
				while(l1LItr.hasPrevious())
					System.out.println("Linked List through Iterator"+l1LItr.previous());
		
			}
	}