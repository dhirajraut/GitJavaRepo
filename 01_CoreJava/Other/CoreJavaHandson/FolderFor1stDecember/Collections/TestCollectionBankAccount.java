//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.LinkedList;
import java.util.TreeSet;
//import java.util.Comparator;

public class TestCollectionBankAccount
	{	public static void main(String [] argv)
			{	ListAccountsWithMap la = new ListAccountsWithMap();
			
				la.addNew("Ganesh", 5000, true);
				System.out.println("First record added.");
				la.addNew("Suresh", 7000, true);
				System.out.println("First record added.");
				la.addNew("Ramesh", 1000, false);
				System.out.println("First record added.");
				
				System.out.println("List after insertion.");
				listRecords(la.listAll());
				
				//la.deleteOld(1);
				System.out.println("List after deletion.");
				listRecords(la.listAll());
				
				Calendar cal = Calendar.getInstance();
				
				cal.set(Calendar.YEAR, 2006);
				cal.set(Calendar.MONTH, 10);
				cal.set(Calendar.DAY_OF_MONTH, 16);
				cal.add(Calendar.MONTH, 10);
				System.out.println("Todays date:"+cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
			}
	
		public static void listRecords(Collection sa)
			{	/*for(int i=0; i<sa.length; i++)
					{	System.out.println(sa[i]);	}*/
				Iterator i = sa.iterator();
				while (i.hasNext())
					System.out.println((SavingAccount)i.next());
			}
	}

class BankAccount
	{	int accNo;
		String accNm;
		float accBal;
		
		static int nextId = 1;
		
		public BankAccount()
			{	accNo = nextId++;	}
		
		public BankAccount(String accNm, float accBal)
			{	this();
				this.accNm = accNm;
				this.accBal = accBal;
			}
		public int getAccNo()
			{	return accNo;	}
		
		public String getAccNm()
			{	return accNm;	}
		
		public  void setAccNm(String accNm)
			{	this.accNm = accNm;	}
		
		public float getAccBal()
			{	return accBal;	}
		
		public String toString()
			{	return "AccNo: "+accNo+"   AccNm: "+accNm+" AccBal: "+accBal;	}
	}

class SavingAccount extends BankAccount
	{	boolean isSalaried;
	
		public SavingAccount()
			{	super();
				isSalaried = false;
			}
		
		public SavingAccount(String accNm, float accBal, boolean isSalaried)
			{	super(accNm, accBal);
				this.isSalaried = isSalaried;
			}
		
		public boolean getIsSalaried()
			{	return isSalaried;	}
		
		public void setIsSalaried(boolean isSalaried)
			{	this.isSalaried = isSalaried;	}
		
		public String toString()
			{	return (super.toString()+"   Is Salaired: "+(isSalaried?"Yes":"No"));	}
	}

class CompareOnAccNo implements Comparator
	{	public int compare(Object o1, Object o2)
			{	BankAccount b1 = (BankAccount) o1;
				BankAccount b2 = (BankAccount) o2;
				
				
				return  (b1.getAccNo()>b2.getAccNo())? -1 : 1;
			}
	}
class ListAccounts
	{	 TreeSet al;
	

		public ListAccounts()
			{	al = new TreeSet(new CompareOnAccNo());	}
		
		public ListAccounts(int iniCapa)
			{	al = new TreeSet(new CompareOnAccNo());	}
		
		public int addNew(String accNm, float accBal, boolean isSalaried)
			{	SavingAccount sa = new SavingAccount(accNm, accBal, isSalaried);
				
				if (search(accNm)==null)
					{	al.add(sa);
						return sa.getAccNo();
					}
				return 0;
			}
		
		public SavingAccount search(int accNo)
			{	Iterator i = al.iterator();
				while (i.hasNext())
					{	SavingAccount sa = (SavingAccount)i.next();
						if (sa.getAccNo()==accNo)
							return sa;
					}
				return null;
			}
		
		public SavingAccount search(String accNm)
			{	Iterator i = al.iterator();
				while (i.hasNext())
					{	SavingAccount sa = (SavingAccount)i.next();
						if (sa.getAccNm().equalsIgnoreCase(accNm))
							return sa;
					}
				return null;
			}
		
		public SavingAccount deleteOld(int accNo)
			{	SavingAccount sa = search(accNo);
				if (sa == null)
					return null;
				al.remove(sa);
				return sa;
			}
		
		public SavingAccount[] listAll()
			{	
				Object [] o = al.toArray();
				SavingAccount[] sa = new SavingAccount[o.length];
				
				for(int i =0; i< o.length; i++)
					{	sa[i] = (SavingAccount)o[i];	}
				
				return sa;
			}
		}

class ListAccountsWithMap
{	 HashMap al;


	public ListAccountsWithMap()
		{	al = new HashMap();	}
	
	public ListAccountsWithMap(int iniCapa)
		{	al = new HashMap(iniCapa);	}
	
	public int addNew(String accNm, float accBal, boolean isSalaried)
		{	SavingAccount sa = new SavingAccount(accNm, accBal, isSalaried);
			
			if (search(accNm)==null)
				{	//al.put(new Integer(sa.getAccNo()), sa);
					al.put(accNm, sa);
					return sa.getAccNo();
				}
			return 0;
		}
	
	public SavingAccount search(String accNm)
		{	SavingAccount sa = (SavingAccount)al.get(accNm);
			return sa;
		}
	
	public SavingAccount deleteOld(String accNm)
		{	SavingAccount sa = search(accNm);
			if (sa == null)
				return null;
			al.remove(sa.getAccNm());
			return sa;
		}
	
	public Collection listAll()
		{	
			Collection o = al.values();
			//SavingAccount[] sa = new SavingAccount[o.size()];
			
			return o;
			/*Iterator it = o.iterator();
			for(int i =0; it.hasNext(); i++)
				{	sa[i] = (SavingAccount)it.next();	}
			
			return sa;*/
		}
	}