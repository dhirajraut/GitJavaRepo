package Module11.collections.sortedsetinterface;
import java.util.*;
class ReverseCompareString implements Comparator{
	public int compare(Object a, Object b){
			String ast = (String) a;   	String bst =(String) b;
			return(bst.compareTo(ast));
	}
}
class TestComparator{
	public static void main(String[ ] args){
		ReverseCompareString rcs = new ReverseCompareString();
		TreeSet ts = new TreeSet(rcs);

		ts.add("A"); 	ts.add("K");	ts.add("D");	ts.add("B");
		ts.add("F");	ts.add("C");	ts.add("G1");	ts.add("G2");

		Iterator it = ts.iterator();
		while(it.hasNext()){
			System.out.println("Next:"+it.next());
		}
	}
}