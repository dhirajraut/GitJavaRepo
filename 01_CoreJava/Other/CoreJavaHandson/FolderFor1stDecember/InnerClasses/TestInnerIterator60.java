import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TestInnerIterator60
	{	public static void main(String [] argv)
		{	MyIterator60 mi = new MyIterator60();
		
			Iterator i = mi.iterate();
			System.out.print("Using Iterator:");
			while(i.hasNext())
				System.out.print(i.next()+ " ");
			
			ListIterator li = mi.listIterator();
			System.out.print("\nUsing List Iterator:");
			while(li.hasNext())
				System.out.print(li.next()+ " ");
			System.out.println();
			while(li.hasPrevious())
				System.out.print(li.previous()+" ");
			
			System.out.println("\n....");
		}
	}	
class MyIterator60
{	String [] list;

	MyIterator60()
		{	list = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J"};	}
	
	private class MyIteration implements Iterator
		{	protected int pos;
		
			private MyIteration()
				{	pos = 0;	}
			
			public boolean hasNext()
				{	return (pos<list.length);	}
	
			public Object next() throws NoSuchElementException
				{	if (pos>= list.length)
						throw new NoSuchElementException();
					return list[pos++];
				}
	
			public void remove()
				{	throw new UnsupportedOperationException();	}
		}
	
	public Iterator iterate()
		{	return new MyIteration();	}
	
	private class MyListIteration extends MyIteration implements ListIterator
		{	public boolean hasPrevious()
				{	return pos>0;	}
		
			public Object previous() throws NoSuchElementException
				{	if (pos<=0)
						throw new NoSuchElementException();
					return list[--pos];
				}
			
			public void add(Object arg0) {	}

			public int nextIndex() {	return 0;	}

			public int previousIndex() {	return 0;	}

			public void remove() {}

			public void set(Object arg0) {}
		}
	
	public ListIterator listIterator()
		{	return new MyListIteration();	}
}