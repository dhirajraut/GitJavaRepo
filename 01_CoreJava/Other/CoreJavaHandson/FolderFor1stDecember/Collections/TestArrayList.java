  import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

// IMPORTANT :  remove() on Iterator does not show expected effect.
// IMPORTANT : remove(), add() and set(Object) are not tried on ListIterator
/** ArrayList allows Iterator and ListIterator both.
/* Methods of ArrayList 
 * Adding new element in an ArrayList
 * 		* At last, at given index
 * 		* List of comlete collection at last or at given index
 * Removing ArrayList elements
 * 		* Clearing all elements from the list.
 * 		* Removing element from the given index.
 * 		* Removing given element whereever be in the list.
 * 		* Removeing all elements from given index to given index.
 * Getting value of specific element
 * 		* Getting value of an element at given index.
 * 		* Getting all elements into string form.
 * Setting value of specific element
 * 		* Setting given object at given index.
 * Searching element
 * 		* Ensuring presence of a given element.
 * 		* Finding first occurance of the given element.
 * 		* Finding last occurance of the given element.
 * 		* Ensuring whether list has any element.
 * 		* Counting number of elements.
 * Other
 * 		* Setting maximum capacity.
 * 		* Creating clone.
 * 		* Treaming to size.
 * 		* Converting to Array.
 */

public class TestArrayList
	{	public static void main(String[] args)
			{	// Create an ArrayList
				ArrayList arl = new ArrayList();
				//LinkedList arl= new LinkedList();
				arl.ensureCapacity(40);
				System.out.println("Initial size of ArrayList:"+arl.size());	// It is always 0
				
				// Add elements to ArrayList. Use add(Object), add(int index, Object)
				arl.add("C");
				arl.add(new Integer(15));
				arl.add("A");
				arl.add(new Float(3.14));
				arl.add(1, "D");
				System.out.println("Size of ArrayList after adding 5 Objects:"+arl.size());		// It is 5 as 5 objects are added.
				
				// Use toString() on ArrayList
				System.out.println("Contents after addition of ArrayList :"+arl);
				
				// Remove elements from ArrayList.  Use remove(Object), remove(int index)
				arl.remove("D");		// Removes object having value "D"
				arl.remove(2);			// Removes item at 2nd subscript position
				System.out.println("Contents after removal of ArrayList :"+arl);
				
				// Clear an ArrayList
				arl.clear();
				System.out.println("Contents after clearing of ArrayList :"+arl);
				
				// Adding Integers to ArrayList to convert to Array
				arl.add(new Integer(10));
				arl.add(new Integer(20));
				arl.add(new Integer(30));
				arl.add(new Integer(40));
				System.out.println("New contents of Integer ArrayList"+arl);
				
				// Convert to array of Objects
				Object [] arlInt = new Object[arl.size()];
				int sum, i;
				arlInt = arl.toArray();
				for(i=0, sum=0; i<arlInt.length; i++)
					sum+=((Integer)arlInt[i]).intValue();
				System.out.println("Sum of values of Array:"+sum);
				
				// Convert to array of int
				Object[] aInt = new Integer[arl.size()];
				aInt = arl.toArray(aInt);
				
				for(i=0; i<aInt.length; i++)
					sum+=((Integer)aInt[i]).intValue();
				
				
				// Ensuring larger capacity and triming a capacity
				//arl.ensureCapacity(6);
				System.out.println("Size after ensuring larger capacity"+arl.size());
				System.out.println("ArrayList after ensuring larger capacity"+arl);
				
				// The trimming occurs on the that part of the dynammically allocated
				// part which does not hold objects.
				arl.remove(2);
				arl.remove(2);
				//arl.trimToSize();
				System.out.println("Size after trimming to smaller capacity"+arl.size());
				System.out.println("ArrayList after trimming to smaller capacity"+arl);
				
				// Using Iterators	Allows only Forword Traversal
				arl.add(new Integer(30));
				arl.add(new Integer(40));
				
				System.out.println("ArrayList before iteration"+arl);
				
				Iterator arlItr=arl.iterator();

				// Apply hasNext() and next()
				while(arlItr.hasNext())
					System.out.println("Iterator Elements in sequence :"+(Integer)arlItr.next());
				arlItr=arl.iterator();
				while (arlItr.hasNext())
					{	arlItr.next();
						arlItr.remove();
					}
				System.out.println("List from iterator after removing elements from iterator");
				while(arlItr.hasNext())
					{	System.out.println("Next Element fro Iterator:"+arlItr.next());	}
				
				System.out.println("List after removing elements from Iterator :"+arl);
				
				// Using ListIterators	Allows traversal in both directions
				arl.add(new Integer(10));
				arl.add(new Integer(20));
				arl.add(new Integer(30));
				arl.add(new Integer(40));
				ListIterator arlLItr = arl.listIterator();
				
				// Apply hasNext(), next(), hasPrevious() and previous() methods
				while(arlLItr.hasNext())
					System.out.println("List Iterator Elements in Sequence :"+(Object)arlLItr.next());
				while(arlLItr.hasPrevious())
					System.out.println("List Iterator Elements in Reverse Sequence :"+(Object)arlLItr.previous());
				// Apply nextIndex() and previousIndex() methods
				while(arlLItr.hasNext())
					{	System.out.print  ("Index of Next :"+arlLItr.nextIndex()+"   ");
						System.out.println("Index of Prev :"+arlLItr.previousIndex());
						arlLItr.next();
					}
			}
}