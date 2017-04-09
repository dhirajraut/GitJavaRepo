package Module09.inbuiltclasses.objectclass.equalsmethod;

class MyObject{

};
class EqualsMethodVersion01{
	public static void main(String args[]){
		MyObject o1=new MyObject();
		MyObject o2=o1;
		MyObject o3=new MyObject();
		
		if(o1.equals(o2))
			System.out.println("o1 equals to o2");
		if(o3.equals(o2))
			System.out.println("o3 equals to o2");
		else
			System.out.println("o3 is not equals to o2");
		if(o1==o3)
			System.out.println("o1 equals to o3");
		else
			System.out.println("o1 not equals to o3");
	}
}


