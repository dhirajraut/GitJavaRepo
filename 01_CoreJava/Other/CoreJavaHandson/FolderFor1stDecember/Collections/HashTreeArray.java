 import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Arrays;

class BankClass
	{	private float curBal;
		private int accNo;
		
		public BankClass()
			{	 }
		public BankClass(int accNo)
			{	this.accNo=accNo; }
		public int getAccNo()
			{	return accNo;	}
	}

class CompareBankClass implements Comparator
	{	public int compare(Object o1, Object o2)
			{	int i1 = ((BankClass)o1).getAccNo();
				int i2 = ((BankClass)o2).getAccNo();
				return((i1<i2)? -1 :(i1==i2)?0:1);
			}
	}

public class HashTreeArray
	{	static final int NoTimes=15000;
		public static void main(String argv[])
			{	// For array list
				ArrayList ar = new ArrayList(NoTimes);
				long ars, are;
				ars=System.currentTimeMillis();
				for(int i = 0; i<NoTimes; i++)
					ar.add(0,new BankClass());
				are=System.currentTimeMillis();
				System.out.println("Time taken by array list "+(are-ars));
				ar.clear();
				ar=null;
				
				//For tree set
				TreeSet tr = new TreeSet(new CompareBankClass());
				long trs, tre;
				trs=System.currentTimeMillis();
				for(int i = 0; i<NoTimes; i++)
					{ 	tr.add(new BankClass(5));}
				tre=System.currentTimeMillis();
				System.out.println("Time taken by Tree set "+(tre-trs));
				Iterator it=tr.iterator();
				for(int i=0; i<10; i++)
					{	BankClass bc = (BankClass)it.next();
						System.out.println(bc.getAccNo());
					}
				tr.clear(); tr=null;
				
				// For Hash set
				HashSet hr = new HashSet();
				long hrs, hre;
				hrs=System.currentTimeMillis();
				for(int i = 0; i<NoTimes; i++)
					{ 	hr.add((new BankClass(i+1)));	}
				hre=System.currentTimeMillis();
				System.out.println("Time taken by Hash set "+(hre-hrs));
				hr.clear(); hr = null;
				
				// For Hash Map
				HashMap hm = new HashMap();
				long hms, hme;
				hms=System.currentTimeMillis();
				for(int i = 0; i<NoTimes; i++)
					{ 	BankClass bc = new BankClass(i+1);
						hm.put(new Integer(bc.getAccNo()), bc);
					}
				//hm.put(new Integer(1000), null);  //Takes null references
				hme=System.currentTimeMillis();
				System.out.println("Time taken by Hash Map "+(hme-hms));
				hm.clear(); hm = null;
				
				//	For Tree Map
				TreeMap tm = new TreeMap();
				long tms, tme;
				tms=System.currentTimeMillis();
				for(int i = 0; i<NoTimes; i++)
					{ 	BankClass bc = new BankClass(i+1);
						tm.put(new Integer(bc.getAccNo()), bc);
					}
				tme=System.currentTimeMillis();
				System.out.println("Time taken by Tree Map "+(tme-tms));
				tm.clear(); tm = null;
			}
	}
