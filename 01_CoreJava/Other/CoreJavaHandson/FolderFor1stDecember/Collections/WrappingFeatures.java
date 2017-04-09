import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collections;

public class WrappingFeatures
	{	HashSet hs  = new HashSet();
	
		public Collection getSetForPrinting()
			{	return Collections.unmodifiableCollection(hs);	}
		
		public HashSet getSetForManipulation()
			{	return hs;	}

		public static void main(String [] argv)
			{	WrappingFeatures wf = new WrappingFeatures();

				Collection hs1 = wf.getSetForPrinting();
				//hs1.add("abc");

				HashSet hs2 = wf.getSetForManipulation();
				hs2.add("pqr");

				// Making array unmodifiable

				final String monthNames[]=
					{ "January", "February", "March", "April"	};

				final List months = Collections.unmodifiableList(Arrays.asList(monthNames));
			}
	}
