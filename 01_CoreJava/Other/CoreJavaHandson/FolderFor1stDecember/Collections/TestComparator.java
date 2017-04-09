 import java.util.*;
import java.util.Comparator;

// A reverse comparator for strings
class ReverseCompareString implements Comparator
	{	public int compare(Object a, Object b)
			{	String ast;
				String bst;
		
				ast=(String) a;
				bst=(String) b;
				
				return(bst.compareTo(ast));
			}
	}

public class TestComparator
	{	public static void main(String[] args)
			{	TreeSet ts = new TreeSet(new ReverseCompareString());
				//TreeSet ts = new TreeSet();
				ts.add("A");
				ts.add("E");
				ts.add("D");
				ts.add("B");
				ts.add("F");
				ts.add("C");
				ts.add("G1");
				ts.add("G2");

				Iterator it = ts.iterator();
				while(it.hasNext())
					{	System.out.println("Next:"+it.next());	}
			}
	}