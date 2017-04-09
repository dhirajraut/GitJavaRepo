package Module03.garbagecollection;

class Employee {
	Employee(){
			System.out.println ("Employee created...");
	}
	protected void finalize () throws Throwable { 
			System.out.println ("\t\tFinalizing...");
	}
}

class GarbageCollectionTest{ 
	public static void main (String [] args) {
			for (int i = 1; i < 15000; i++){ 
				Employee x = new Employee();
			}
	}
}

	    

