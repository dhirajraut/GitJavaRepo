import java.util.*;
// IMPORTANT : A treeset can be traversed through iterator()
// IMPORTANT : A treeset does not allow ListIterator as treeset does not implement List 
public class TestTreeSet
	{	public static void main(String[] args)
			{	TreeSet ts = new TreeSet();
				
				// Adding nodes to a tree
				ts.add("C"); ts.add("A"); ts.add("B"); ts.add("D"); ts.add("E"); ts.add("F");
				
				// Getting Size
				System.out.println("Tree size :"+ts.size());
				
				// Printing contents
				System.out.println("Tree contents :"+ts);
				
				// Getting first and last element
				System.out.println("The very first element :"+ts.first());
				System.out.println("The very last element :"+ts.last());
				
				// Whether tree is empty?
				System.out.println("Is tree empty?"+ts.isEmpty());
				
				// Removing elements from tree
				ts.remove("F");
				System.out.println("Tree contents after removing 'F' :"+ts);
				
				// Searching a presence of object in a tree
				System.out.println("Whether tree contains 'D'?"+ts.contains("D"));
				
				// Converting a tree into array
				Object [] arrTree = new Object[ts.size()];
				arrTree=ts.toArray();
				for(int i=0; i<arrTree.length; i++)
					System.out.println("The "+i+"th object is :"+arrTree[i]);
				
				// Implementing Iterator and List Iterator
				Iterator l1Itr = ts.iterator();
				System.out.println("Linked List through Iterator :");
				while(l1Itr.hasNext())
					System.out.println(l1Itr.next()+",");
			}
}
