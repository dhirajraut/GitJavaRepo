package Module14.threadscheduling;

class Thread1 extends Thread{
	public void run(){
		for(int i =0;i<5;i++){
			System.out.println("Thread1");
		}
	}
}

class Thread2 extends Thread{
	Thread1 t;
   	public Thread2(Thread1 t){
   		this.t = t;
    }
    public void run(){
    	for(int i =0;i<5;i++){
    		System.out.println("Thread2");
    		t.interrupt();
    	}
    }
}


public class InterruptDemo {
	public static void main(String[] args){
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2(t1);
		t1.start();
		t2.start();
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
