package Module13.serialization;
import java.io.*;

class BankAccount{
	private double balance;
		
	public double getBalance(){
		return balance;	
	}
	protected void setBalance(double balance){
		this.balance=balance;
	}	
}

class SavingAccount extends BankAccount implements Serializable {
	private boolean isSalaried;
		
	public boolean isSalaried(){
		return isSalaried;													
	}

	public void setSalaried(boolean isSalaried){
		this.isSalaried=isSalaried;
	}

	private void readObject(ObjectInputStream is) throws IOException {
		setBalance(is.readDouble());
		this.isSalaried=is.readBoolean();			
	}

	private void writeObject(ObjectOutputStream os) throws IOException{
		os.writeDouble(getBalance());
		os.writeBoolean(isSalaried);
	}		
}

class BankDemo {
	public static void main(String[] args) {
		SavingAccount sa1=new SavingAccount();		
		try {
			FileOutputStream fos=new FileOutputStream("bank.ser");
			ObjectOutputStream oos=new ObjectOutputStream(fos);		
			sa1.setBalance(1000);
			sa1.setSalaried(true);			
			oos.writeObject(sa1);
			fos.close();			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}		
	}
}
class BankDemo2 {
	public static void main(String[] args) {
		try {
			FileInputStream fis=new FileInputStream("bank.ser");
			ObjectInputStream ois=new ObjectInputStream(fis);
			SavingAccount sa1=(SavingAccount)ois.readObject();
			System.out.println("Balance :"+sa1.getBalance());
			System.out.println("isSalaried"+sa1.isSalaried());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}



