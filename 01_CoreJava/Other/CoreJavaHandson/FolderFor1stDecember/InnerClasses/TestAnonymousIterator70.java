import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TestAnonymousIterator70
	{	public static void main(String [] argv)
			{	myIterator70 mi = new myIterator70();

				Iterator i = mi.iterate();
				
				while(i.hasNext())
					System.out.println(i.next());
				
				System.out.println("---------");
				
				ListIterator li = mi.listIterator();
				
				while(li.hasNext())
					System.out.println(li.next());
				System.out.println("---------");
				while(li.hasPrevious())
					System.out.println(li.previous());
				System.out.println("Throwing an exception....");
				i.remove();
			}
	}

class myIterator70
	{	String [] list;
	
		myIterator70()
			{	list = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J"};	}
		
		public Iterator iterate()
			{	Iterator i = new Iterator()
					{	private int pos = 0;
					
						// Initialization Block
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
				
					};
				return i;
			}
		
		public ListIterator listIterator()
			{	ListIterator li = new ListIterator()
					{	private int pos;

						// Initialization Block
						{	pos = 0;	}
						
						public boolean hasNext()
							{	return pos<list.length;	}
						
						public boolean hasPrevious()
							{	return pos>=0;	}
						
						public Object next() throws NoSuchElementException
							{	if (pos>= list.length)
									throw new NoSuchElementException();
								return list[pos++];
							}
						
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
					};
				return li;
			}
	}
