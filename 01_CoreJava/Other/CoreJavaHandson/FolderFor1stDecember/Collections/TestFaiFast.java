import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
public class TestFaiFast
	{	public static void main(String[] args)
			{	ArrayList al = new ArrayList();
				List lst = Collections.synchronizedList(al);
				
				ThreadTraverse tt = new ThreadTraverse("Thrd Trav.", lst);
				tt.start();
				
				ThreadAdd ta = new ThreadAdd("Thrd Add.", lst);
				ta.start();
			}
	}
class ThreadAdd extends Thread
	{	List lst;
		public ThreadAdd(String nm, List lst)
			{	super(nm);
				this.lst = lst;
			}
		
		public void run()
			{	String [] monNm = { "July", "August", "September",
									"October",	"November", "December"
								  };
				for(int i=0; i<monNm.length; i++)
					{	lst.add(monNm[i]);
						try {	sleep((long)(3000*Math.random()));	}
						catch (InterruptedException e)
							{	e.printStackTrace();	}
					}
			}
	}
class ThreadTraverse extends Thread
	{	List lst;
		public ThreadTraverse(String nm, List lst)
			{	super(nm);
				this.lst = lst;
			}
		public void run()
			{	lst.add("January");
				lst.add("February");
				lst.add("March");
				lst.add("April");
				lst.add("May");
				lst.add("June");
				
				try {
						//for(Object st : lst)
						Iterator it = lst.iterator();
						while(it.hasNext())
							{	String st = (String) it.next();
								System.out.println(st);
								try {	sleep((long)(3000*Math.random()));	}
								catch (InterruptedException e)
									{	e.printStackTrace();	}
							}
					}
				catch(ConcurrentModificationException cme)
					{	System.out.println("Iterator failed fast.");
						cme.printStackTrace();
					}
			}
	}