import java.util.*;

// run this program using input as c:>java testShuffle a b c d e f
// the out put us a d e f b c  the elements are randomly shuffled.

public class testShuffle
	{ public static void main(String args[])
		{ List l = new ArrayList();
		  for(int i=0; i<args.length; i++)
			l.add(args[i]);
		  Collections.shuffle(l, new Random());
		  System.out.println(l);
		}
	}