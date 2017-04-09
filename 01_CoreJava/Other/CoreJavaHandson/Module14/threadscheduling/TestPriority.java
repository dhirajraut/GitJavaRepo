package Module14.threadscheduling;

class Priority implements Runnable{
	public void run(){
		for(int i=0 ; i<15; i++){
	  		System.out.println("Run by :  " + Thread.currentThread().getName());
        }
	}
}

public class TestPriority{
		public static void main(String[] args){
			Priority p = new Priority();
			
			Thread t1 = new Thread(p);
			Thread t2 = new Thread(p);
			
			t1.setPriority(1);  
	        t2.setPriority(10);
			
	        t1.start();  
	        t2.start();
		}
}

	        



