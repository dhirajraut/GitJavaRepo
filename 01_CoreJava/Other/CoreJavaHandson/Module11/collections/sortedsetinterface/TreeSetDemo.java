package Module11.collections.sortedsetinterface;

import java.util.*; 
public class TreeSetDemo{
	public static void main(String[] args){
		TreeSet ts = new TreeSet();
 		ts.add("C"); ts.add("A"); ts.add("B"); ts.add("D"); ts.add("E"); ts.add("F");
	  System.out.println("Tree size :"+ts.size());
		System.out.println("Tree contents :"+ts);
		System.out.println("The very first element :"+ts.first());
		System.out.println("The very last element :"+ts.last());
		System.out.println("Is tree empty?"+ts.isEmpty());
		ts.remove("F");
		System.out.println("Tree contents after removing 'F' :"+ts);
		System.out.println("Whether tree contains 'D'?"+ts.contains("D"));
		Object [] arrTree = new Object[ts.size()];
		arrTree=ts.toArray();
		for(int i=0; i<arrTree.length; i++)
				System.out.println("The "+i+"th object is :"+arrTree[i]);
		Iterator l1Itr = ts.iterator();
		System.out.println("Linked List through Iterator :");
		while(l1Itr.hasNext())
				System.out.println(l1Itr.next()+",");}
}
