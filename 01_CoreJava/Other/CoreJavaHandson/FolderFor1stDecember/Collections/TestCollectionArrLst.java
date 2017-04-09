import java.util.Collection;

public class TestCollectionArrLst
	{	public static void main(String []argv)
			{	CollectionArrLst ca = new CollectionArrLst();
			
				ca.addNewAccount(new SavingBankAccount(101, "Ramesh", 300));
				ca.addNewAccount(new SavingBankAccount(102, "Ganesh", 400));
				ca.addNewAccount(new SavingBankAccount(103, "Rajesh", 500));
				ca.addNewAccount(new SavingBankAccount(104, "Mukesh", 600));
				
				System.out.println("\nWhole list...");
				ca.listAll();
				
				Collection c = ca.getUnmodifiable();
				c.add(new SavingBankAccount(105, "Akhilesh", 7000));
				
				
			}
	
	}
