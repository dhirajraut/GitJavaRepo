package Module14.threadscheduling;

public class JoinDemo extends Thread {
	public void run(){
		for(int i=0 ; i<15; i++){
			System.out.println("Run by :  " + Thread.currentThread().getName());
		}
	}
	public static void main(String[] args){
		try{
			System.out.println("Starting main Thread");
			JoinDemo jd1 = new JoinDemo();
			JoinDemo jd2 = new JoinDemo();
			
			jd1.start();
			jd2.start();
			
			jd1.join();
			jd2.join();
			
			System.out.println("Finishing main Thread");
		} 
		catch(InterruptedException ie){
			System.out.println("Exception");
		}
	}
}


