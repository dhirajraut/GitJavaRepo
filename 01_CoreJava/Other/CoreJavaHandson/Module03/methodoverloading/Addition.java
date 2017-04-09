package Module03.methodoverloading;

public class Addition {
	public void add(int a ,int b){
		System.out.println("Adding 2 integers...");
		System.out.println("sum : "+(a+b));
	}
	public void add(int a ,int b,int c){
		System.out.println("Adding 3 integers...");
		System.out.println("sum : "+(a+b+c));
	}
	public void add(float a ,float b){
		System.out.println("Adding 2 float values...");
		System.out.println("sum : "+(a+b));
	}
	
	public static void main(String[] args){
		Addition a = new Addition();
		a.add(10,20);
		a.add(10,20,30);
		a.add(10.0f,20.0f);
	}


}
