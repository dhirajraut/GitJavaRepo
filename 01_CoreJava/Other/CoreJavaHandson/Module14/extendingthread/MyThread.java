package Module14.extendingthread;

class MyThread extends Thread{
	MyThread(String name){
		super(name);
	}
	
	public void run(){
		for (int i=0 ; i<100 ; i++){
			System.out.println("Run By : "+ getName());
		}
	}
	
	public static void main(String[] args) {
		MyThread mt1 = new MyThread("MyThread-1");
		MyThread mt2 = new MyThread("MyThread-2");
		mt1.start();
		mt2.start();
	}
}
