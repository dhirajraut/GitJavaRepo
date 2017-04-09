

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Account;
import entities.Address;
import entities.CurrentAccount;
import entities.Customer;
import entities.SavingsAccount;


public class Assignment {
	public static void main (String args[]) {

		/* Common Steps. */
		Session session = Util.configureHibernate();

		insertData(session);

		session.close();
	}
	
	private static void insertData(Session session){
		for (int ctr = 1; ctr <= 3; ctr++) {
			Address address = new Address();
			address.setStreet("Street" + ctr);
			address.setCity("City" + ctr);
			address.setPinCode("PinCode" + ctr);

			Customer customer = new Customer();
			customer.setName("Customer" + ctr);
			customer.setAddress(address);
			
			SavingsAccount account1 = new SavingsAccount();
			account1.setAccountHolder(customer.getName());
			account1.setBalance(1000);
			account1.setMinimumBalance(100);
			account1.setCustomer(customer);
			
			CurrentAccount account2 = new CurrentAccount();
			account2.setAccountHolder(customer.getName());
			account2.setBalance(100);
			account2.setOverdraft(10000);
			account2.setCustomer(customer);
			
			List<Account> accountList = new ArrayList<Account>();
			accountList.add(account1);
			accountList.add(account2);
			customer.setAccountList(accountList);
			
			Transaction transaction = session.beginTransaction(); // Mandatory in hibernate.
			session.save(customer);
			transaction.commit();
		}
		session.close();
	}
}
