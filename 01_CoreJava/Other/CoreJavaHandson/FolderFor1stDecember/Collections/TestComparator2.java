import java.util.*;
class CompareOnSurname implements Comparator
	{	public int compare(Object a1, Object a2)
			{	String aSurname, bSurname;
				String aName, bName;
				int i, j;
				
				aName = (String)a1;
				bName = (String) a2;
				i=aName.lastIndexOf(" ");
				j=bName.lastIndexOf(" ");
				
				aSurname=aName.substring(i);
				bSurname=bName.substring(j);
				
				return(bSurname.compareTo(aSurname));
			}
	}
public class TestComparator2
	{	public static void main(String[] args)
			{	TreeSet ts = new TreeSet(new CompareOnSurname());

				ts.add("Yogesh Balwant Deshpande");
				ts.add("Ramesh Sudhir Mishra");
				ts.add("Balchandra Sudhanshu Nair");
				ts.add("Rahul Krutichand Raheja");
				ts.add("Dilip Dinesh Shrivastav");
				ts.add("Shrichand Shyamlal Dudhani");
				
				Iterator it = ts.iterator();
				while (it.hasNext())
					System.out.println(it.next());
			}
	}