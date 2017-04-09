import java.util.*;

public class TestHashSet
	{	public static void main(String[] args)
			{	HashSet hs = new HashSet();
				hs.add("A");
				hs.add("B");
				hs.add((Object)new Double(1.2));
				hs.add((Object)new Character('C'));
				System.out.println(hs);
				if (hs.contains("A"))
					System.out.println("Hash Set contains - A");
				System.out.println("The Hash set contains :"+hs.size()+"Elements.");
				Iterator it = hs.iterator();
			
				while (it.hasNext())
					{	System.out.println(it.next().hashCode());	}
				hs.clear();
				System.out.println("After The Hash set contains :"+hs.size()+"Elements.");
			}
	}
