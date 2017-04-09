package Module14.runnable;

public class MyRunnable implements Runnable {
	
	public void run(){
	   for(int i=0 ; i<100 ; i++){
		  System.out.println("Run By : "+ Thread.currentThread().getName());
	   }
	}
		
	public static void main(String[] args) {
		MyRunnable mt = new MyRunnable();
		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt,"MyThread-2");
		t1.start();
		t2.start();
	}
}


