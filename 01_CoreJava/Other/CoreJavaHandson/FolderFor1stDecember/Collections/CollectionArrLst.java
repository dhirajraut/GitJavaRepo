import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;
import java.util.TreeSet;

class ComparePolicy implements Comparator
	{	public int compare(Object o1, Object o2)
			{	SavingBankAccount sa1 = (SavingBankAccount) o1;
				SavingBankAccount sa2 = (SavingBankAccount) o2;
				int accNo1 = sa1.getAccNo();
				int accNo2 = sa2.getAccNo();
				if (accNo1<accNo2)
					return -1;
				else if (accNo1==accNo2)
					return 0;
				else
					return 1;
			}
	}

public class CollectionArrLst
	{	//ArrayList al = new ArrayList();
		//HashSet al = new HashSet();
		TreeSet al = new TreeSet(new ComparePolicy());
		public void addNewAccount(SavingBankAccount sba)
			{	al.add(sba);
				//System.out.println(sba+" it is added.");
			}
		
		public SavingBankAccount[] listAll()
			{	Iterator i = al.iterator();	
				SavingBankAccount[] sba = new SavingBankAccount[al.size()];
				for(int j=0; i.hasNext(); j++)
					{	sba[j] = (SavingBankAccount)i.next();
						//System.out.println(sba);
					}
				return sba;
			}
		
		public int search(int accNo)
			{	Iterator i = al.iterator();
				
				for(int j = 0; i.hasNext(); j++)
					{	SavingBankAccount sba = (SavingBankAccount)i.next();
						if (sba.getAccNo()==accNo)
							return j;
					}
				return -1;
			}
		
		public void deleteOldAccount(int accNo)
			{	int n = search(accNo);
				//al.remove(n);
				System.out.println("Acc. No. "+ accNo+" is removed.");
			}
		
		public Collection getUnmodifiable()
			{	Collection c = Collections.unmodifiableCollection(al);
				return c;
			}
		public Collection getSynchronized()
			{	Collection c = Collections.synchronizedCollection(al);
				return c;
			}
		public static void main(String [] argv)
			{	CollectionArrLst cal = new CollectionArrLst();
			
				cal.addNewAccount(new SavingBankAccount(101, "Ramesh", 5000));
				cal.addNewAccount(new SavingBankAccount(102, "Ganesh", 6000));
				cal.addNewAccount(new SavingBankAccount(103, "Mukesh", 7000));
				cal.addNewAccount(new SavingBankAccount(104, "Yogesh", 8000));
				
				SavingBankAccount[] sa = cal.listAll();
				for(int i=0; i<sa.length; i++)
					System.out.println(sa[i]);
			}
	}

class SavingBankAccount
	{	int accNo;
		String accNm;
		float accBal;
		SavingBankAccount(int accNo, String accNm, float accBal)
			{	this.accNo = accNo;
				this.accBal = accBal;
				this.accNm = accNm;
			}
		
		public int getAccNo()
			{	return accNo;	}
		
		public String toString()
			{	return "Acc.No:"+accNo+" Name:"+accNm+" Balance:"+accBal;	}
	}